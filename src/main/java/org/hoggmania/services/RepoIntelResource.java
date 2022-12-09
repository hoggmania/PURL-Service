package org.hoggmania.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*; // wildcard for brevity
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.parameters.*;

import io.quarkus.logging.Log;
import io.smallrye.common.constraint.NotNull;

import org.hoggmania.services.model.Analysis;
import org.hoggmania.services.model.Classification;
import org.hoggmania.services.model.Issue;
import org.hoggmania.services.model.Repo;
import org.hoggmania.services.model.ServiceException;
import org.hoggmania.services.model.VulnScore;
import org.hoggmania.services.model.Repo.PolicyFailureReason;

import dev.deps.schema.*;
import dev.deps.service.*;

import com.github.packageurl.MalformedPackageURLException;
import com.github.packageurl.PackageURL;

import snyk.model.*;

@Path("repository")

public class RepoIntelResource {

    public final static String RepoMediaType_v1 = "application/vnd.repo.vuln-v1.0+json";
    private final static String example_purl = "pkg:maven/log4j/log4j@1.2.17";

    private final EnumSet<VulnScore.Severity> disallowed = EnumSet.of(VulnScore.Severity.HIGH,
            VulnScore.Severity.CRITICAL);

    @Inject
    SnykProxyService snykService;

    @Inject
    DepsDevProxyService depsService;

    @GET
    @Produces(RepoMediaType_v1)
    @Path("oss")
    @APIResponses(value = {
            @APIResponse(responseCode = "400", description = "Invalid purl supplied"),
            @APIResponse(responseCode = "200", description = "Finds vulnerabilities for a pacakage url", content = @Content(schema = @Schema(type = SchemaType.OBJECT, implementation = Repo.class)))
    })
    public Response getRepo(@Context HttpHeaders headers,
            @Parameter(name = "purl", description = "Package URL", required = true, allowEmptyValue = false, example = example_purl) @QueryParam("purl") @NotNull String purl) {

        PackageURL purl_parsed;

        try {
            purl_parsed = new PackageURL(purl);
            
        } catch (MalformedPackageURLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        Repo repo = new Repo(getTimestampForNow(), purl_parsed);
        
        try{
            // Get Deps.dev
            VersionSummary vs = depsService.getFreshIntel(purl_parsed);

            if (vs == null || vs.getVersion() == null) {
                // So version does not exist (204 No Content or 404 Not Found)
                return Response.status(Response.Status.fromStatusCode(404)).build();
            } else if (vs.getVersion().getProjects() == null
                    || vs.getVersion().getProjects().size() != 1
                    || vs.getVersion().getProjects().get(0).getScorecardV2() == null) {
                repo.getPolicyFailureReason().add(PolicyFailureReason.METADATA);
                repo.getIssues()
                        .add(new Issue("NO_SCORECARD", PolicyFailureReason.METADATA.name(), null, null, null, null,
                                null));
            } else {
                decorateWithAging(repo, vs);
                decorateWithScorcard(repo, vs);
            }

            
        } catch (ServiceException e) {
            if (e.getStatusCode() == Response.Status.NOT_IMPLEMENTED.getStatusCode()) {
                repo.getPolicyFailureReason().add(PolicyFailureReason.METADATA);
                repo.getIssues()
                        .add(new Issue("NO_METADATA_SOURCE", PolicyFailureReason.METADATA.name(), null, null, null, null,
                                null));
            } else {
                return Response.status(Response.Status.fromStatusCode(e.getStatusCode())).build();
            }
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }



        try{
            // Get Snyk
            OSSFindings findings = snykService.getFreshIntel(purl_parsed);
            decorateSnyk(repo, findings.getData(), disallowed);
            return Response.ok().entity(repo).build();

        } catch (ServiceException e) {
            return Response.status(Response.Status.fromStatusCode(e.getStatusCode())).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(e.getMessage()).build();
        } catch (NoContentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    private void decorateWithScorcard(Repo repo, VersionSummary vs) {

        ScorecardV2 sc = vs.getVersion().getProjects().get(0).getScorecardV2();
        List<Check> ch = sc.getCheck();
        for (Check check : ch) {
            if (check.getName().equalsIgnoreCase("Maintained")) {
                if (check.getScore() < 2) {
                    repo.getPolicyFailureReason().add(PolicyFailureReason.METADATA);
                    repo.getIssues().add(new Issue("MAINTAINED_90DAY_UNDER_2", PolicyFailureReason.METADATA.name(),
                            null, null, null, null, null));
                } else if (check.getScore() < 4) {
                    repo.getPolicyFailureReason().add(PolicyFailureReason.METADATA);
                    repo.getIssues().add(new Issue("MAINTAINED_90DAY_UNDER_4", PolicyFailureReason.METADATA.name(),
                            null, null, null, null, null));
                }
            }
        }

    }

    private void decorateWithAging(Repo repo, VersionSummary vs) {
        Log.info("DefaultVersion=" + vs.getDefaultVersion());
        Log.info("Version=" + vs.getVersion().getVersion());
        Log.info("RefreshedAt=" + vs.getVersion().getRefreshedAt());

        repo.setCreatedAt(vs.getVersion().getCreatedAt());
        repo.setRefreshedAt(vs.getVersion().getRefreshedAt());
        repo.setIsDefault(false);
        if (vs.getVersion().getIsDefault() != null) {
            repo.setIsDefault(vs.getVersion().getIsDefault());
            // VS createdAt will be null, so set as same as refresh
            repo.setCreatedAt(vs.getVersion().getRefreshedAt());
        }
        Log.debug("CreatedAt=" + vs.getVersion().getCreatedAt());
        Instant in = Instant.ofEpochSecond(vs.getVersion().getCreatedAt()).atOffset(ZoneOffset.UTC).toInstant();
        Log.debug("CreatedAt=" + in);
        Instant in2 = Instant.now().atOffset(ZoneOffset.UTC).minus(Period.ofYears(2)).toInstant();
        Instant in5 = Instant.now().atOffset(ZoneOffset.UTC).minus(Period.ofYears(5)).toInstant();

        boolean ageIssue = false;
        if (in.isBefore(in2)) {
            // ok under 2
            Log.info("CreatedAt is older than 2 years ");
            repo.getIssues()
                    .add(new Issue("OLDER_THAN_2_YEARS", PolicyFailureReason.AGE.name(), null, null, null, null, null));
            ageIssue = true;
        }

        if (in.isBefore(in5)) {
            // ok under 5
            Log.info("CreatedAt is older than 5 years ");
            repo.getIssues()
                    .add(new Issue("OLDER_THAN_5_YEARS", PolicyFailureReason.AGE.name(), null, null, null, null, null));
            ageIssue = true;
        }

        if (ageIssue) {
            repo.getPolicyFailureReason().add(PolicyFailureReason.AGE);
        }
    }

    @GET
    @Produces(RepoMediaType_v1)
    @Path("snyk")
    @APIResponses(value = {
            @APIResponse(responseCode = "400", description = "Invalid purl supplied"),
            @APIResponse(responseCode = "200", description = "Finds vulnerabilities for a pacakage url", content = @Content(schema = @Schema(type = SchemaType.OBJECT, implementation = Repo.class)))
    })
    public Response getSnyk(@Context HttpHeaders headers,
            @Parameter(name = "purl", description = "Package URL", required = true, allowEmptyValue = false, example = example_purl) @QueryParam("purl") @NotNull String purl) {

        try {
            OSSFindings findings = snykService.getFreshIntel(new PackageURL(purl));
            return Response.ok().entity(findings).build();

        } catch (ServiceException e) {
            return Response.status(Response.Status.fromStatusCode(e.getStatusCode())).build();
        } catch (MalformedPackageURLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(e.getMessage()).build();
        } catch (NoContentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(RepoMediaType_v1)
    @Path("deps")
    @APIResponses(value = {
            @APIResponse(responseCode = "400", description = "Invalid purl supplied"),
            @APIResponse(responseCode = "200", description = "Finds info from deps.dev for a package url", content = @Content(schema = @Schema(type = SchemaType.OBJECT, implementation = Repo.class)))
    })
    public Response getDeps(@Context HttpHeaders headers,
            @Parameter(name = "purl", description = "Package URL", required = true, allowEmptyValue = false, example = example_purl) @QueryParam("purl") @NotNull String purl) {

        try {
            VersionSummary vs = depsService.getFreshIntel(new PackageURL(purl));
            return Response.ok().entity(vs).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.fromStatusCode(e.getStatusCode())).build();
        } catch (MalformedPackageURLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(e.getMessage()).build();
        } catch (NoContentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    public static void decorateSnyk(Repo repo, final List<Datum> data, final EnumSet<VulnScore.Severity> severityThreshold) {
        //Vector<Issue> issues = new Vector<Issue>();
        // data[].id/type/attributes[].problems[]*/servities[]* aggregate over all
        // effective_severity_level

        List<Analysis> lAnalysis = new ArrayList<>();
        lAnalysis.add(new Analysis(Analysis.State.AFFECTED, null, Analysis.VendorResponse.set_wnf_upgrade, null));

        //List<PolicyFailureReason> pflist = PolicyFailureReason.toList(null);

        for (Datum d : data) {
            Vector<Classification> classifications = new Vector<Classification>();
            for (Problem p : d.getAttributes().getProblems()) {
                classifications.add(new Classification(p.getId(), p.getSource()));
            }
            Vector<VulnScore> scores = new Vector<VulnScore>();

            for (Severity s : d.getAttributes().getSeverities()) {

                Log.debug("Severity level" + s.getLevel());
                VulnScore.Severity sev = VulnScore.Severity.fromString(s.getLevel());
                Log.debug("Severity level parsed as " + sev);

                if (severityThreshold.contains(sev)) {
                    if (!repo.getPolicyFailureReason().contains(PolicyFailureReason.VULN_SEVERITY))
                    repo.getPolicyFailureReason().add(PolicyFailureReason.VULN_SEVERITY);
                }
                scores.add(new VulnScore(s.getSource(), sev, s.getScore(), s.getVector()));

            }
            repo.getIssues().add(new Issue(d.getId(), d.getType().toUpperCase(), d.getAttributes().getCreatedAt(),
                    d.getAttributes().getUpdatedAt(), classifications, scores, lAnalysis));
        }
    }

    public static String getTimestampForNow() {
        SimpleDateFormat sdfWithUTCTimezone = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdfWithUTCTimezone.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdfWithUTCTimezone.format(System.currentTimeMillis());
    }

}
