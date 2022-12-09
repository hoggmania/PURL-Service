package dev.deps.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.packageurl.PackageURL;

import dev.deps.schema.VersionSummary;
import io.quarkus.cache.CacheResult;
import io.quarkus.logging.Log;

import org.apache.http.*;
import org.apache.http.client.config.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.hoggmania.services.model.ServiceException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import java.io.IOException;

@Singleton
public class DepsDevProxyService {

    private CloseableHttpClient httpClient;
    private HttpHost target;
    private RequestConfig config;

    /*
     * 
     * pkg:bitbucket/birkenfeld/pygments-main@244fd47e07d1014f0aed9c

pkg:deb/debian/curl@7.50.3-1?arch=i386&distro=jessie

pkg:docker/cassandra@sha256:244fd47e07d1004f0aed9c
pkg:docker/customer/dockerimage@sha256:244fd47e07d1004f0aed9c?repository_url=gcr.io

pkg:gem/jruby-launcher@1.1.2?platform=java
pkg:gem/ruby-advisory-db-check@0.12.4

pkg:github/package-url/purl-spec@244fd47e07d1004f0aed9c

pkg:golang/google.golang.org/genproto#googleapis/api/annotations

pkg:maven/org.apache.xmlgraphics/batik-anim@1.9.1?packaging=sources
pkg:maven/org.apache.xmlgraphics/batik-anim@1.9.1?repository_url=repo.spring.io%2Frelease

pkg:npm/%40angular/animation@12.3.1
pkg:npm/foobar@12.3.1

pkg:nuget/EnterpriseLibrary.Common@6.0.1304

pkg:pypi/django@1.11.1

pkg:rpm/fedora/curl@7.50.3-1.fc25?arch=i386&distro=fedora-25
pkg:rpm/opensuse/curl@7.56.1-1.1.?arch=i386&distro=opensuse-tumbleweed
     */


    protected enum PkgManager { //Cargo/Go/Maven/PyPi/npm
        Cargo("cargo","", "cargo", true),
        
        // pkg:golang/google.golang.org/genproto@v0.0.0-20221205194025-8222ab48f5fc 
        //maps to https://deps.dev/_/s/go/p/google.golang.org%2Fgenproto/v/v0.0.0-20221206210731-b1a01be3a5f6
        Go("go", "%2F", "golang", true), 
        GoDep("go", "%2F", "go", true),
        
        // pkg:maven/log4j/log4j@1.2.17
        Maven("maven", "%3A", "maven", true),


        
        PyPi("pypi", null, "pypi", true),
        Npm("npm", "%2F", "npm", true),
        

        /* Unsupported */
        NotSupported("none", "%2F", "none", false), 
        //pkg:deb/debian/libssl1.1@1.1.0k-1~deb9u1?distro=debian-9.9 map
        Deb("deb", "%2F", "deb", false), 
        Rpm("rpm", "%2F", "rpm", false), 
        Nuget("nuget", "%2F", "nuget", false), 
        Docker("docker", "%2F", "docker", false), 
        Ruby("ruby", "%2F", "ruby", false),
        Bitbucket("bitbucket", "%2F", "bitbucket", false),
        github("github", "%2F", "github", false);


        

        protected String name, pkgsep, parse;
        protected boolean supported = true;

        private PkgManager(String name, String pkgsep, String parse, boolean supported) {
            this.name = name;
            this.pkgsep = pkgsep;
            this.parse = parse;
            this.supported = supported;
        }
        

        public static PkgManager fromString(String s) {
            for(PkgManager pkg : PkgManager.values()){
                if (s.equalsIgnoreCase(pkg.parse)) return pkg;
            }
            return NotSupported;
        }
    }

    @PostConstruct
    void initialiase() throws Exception {
        httpClient = HttpClients.createDefault();
    }

    @PreDestroy
    void finalise() {
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String constructURL(PackageURL purl) throws ServiceException{
        // org.neo4j %3A neo4j-kernel /v/ 5.2.0
        Log.info(purl);
        //Not working https://deps.dev/_/s/go/p/google.golang.org %3A genproto/v/v0.0.0-20221205194025-8222ab48f5fc 
        //            https://deps.dev/go/google.golang.org%2Fgenproto/v0.0.0-20221205194025-8222ab48f5fc
        //            
        //  Works     https://deps.dev/_/s/go/p/google.golang.org %2F genproto/v/v0.0.0-20221206210731-b1a01be3a5f6
        //Cargo/Go/Maven/PyPi/npm
        
        PkgManager pkg = PkgManager.fromString(purl.getType());
        if (pkg == PkgManager.NotSupported) {
            Log.info("Unsupported type "+ purl);
            throw new ServiceException(Response.Status.FORBIDDEN.getStatusCode());
        }
        if (!pkg.supported) {
            Log.info("Unsupported type "+ purl);
            throw new ServiceException(Response.Status.NOT_IMPLEMENTED.getStatusCode());
        }


      

        String url = "https://deps.dev/_/s/"+pkg.name+"/p/" + purl.getNamespace() + pkg.pkgsep + purl.getName() + "/v/"  + purl.getVersion();
        Log.info(url);
        return url;
    }

    @CacheResult(cacheName = "deps.dev-cache")
    public VersionSummary getFreshIntel(PackageURL purl) throws IOException, ServiceException {



        HttpGet request = new HttpGet(constructURL(purl));
        request.addHeader("content-type", "application/json");

        CloseableHttpResponse response;
        // Now check if we are proxy or not
        if (config != null) {
            Log.debug("config is set");
            request.setConfig(config);
            response = httpClient.execute(target, request);
        } else {
            Log.debug("config is not set");
            response = httpClient.execute(request);
        }
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            Log.debug("Status code " + response.getStatusLine().getStatusCode());
            Log.debug("Payload " + new String(response.getEntity().getContent().readAllBytes()));
            throw new ServiceException(response.getStatusLine().getStatusCode());
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            VersionSummary findings = objectMapper.readValue(response.getEntity().getContent(), VersionSummary.class);
            return findings;
        } finally {
            response.close();
        }
    }

    public Response getFreshIntelRaw(PackageURL purl) throws IOException, ServiceException {

        HttpGet request = new HttpGet(constructURL(purl));
        request.addHeader("content-type", "application/json");
        CloseableHttpResponse response = httpClient.execute(request);

        try {
            return javax.ws.rs.core.Response.ok((StreamingOutput) output -> {
                try {
                    output.write(response.getEntity().getContent().readAllBytes());
                    output.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).build();
        } finally {
            response.close();
        }

    }

    public static boolean isCapable(final PackageURL purl) {
        return purl != null
                && purl.getScheme() != null
                && purl.getType() != null
                && purl.getName() != null
                && purl.getVersion() != null;
    }

}