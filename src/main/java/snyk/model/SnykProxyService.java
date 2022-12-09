package snyk.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.packageurl.PackageURL;

import io.quarkus.cache.CacheResult;
import io.quarkus.logging.Log;

import org.apache.http.*;
import org.apache.http.client.config.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.hoggmania.services.model.ServiceException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import java.io.IOException;
import java.util.Optional;

@Singleton
public class SnykProxyService {
    private static String SNYK_DOMAIN = "https://api.snyk.io";
    private static String SNYK_API_ENDPOINT = "/issues?version=2022-09-15";

    @Inject
    @ConfigProperty(name = "SNYK_TOKEN")
    String SNYK_TOKEN;

    @Inject
    @ConfigProperty(name = "SNYK_ORGID")
    String SNYK_ORGID;

    @ConfigProperty(name = "HTTP_PROXY")
    Optional<String> HTTP_PROXY;

    @ConfigProperty(name = "HTTPS_PROXY")
    Optional<String> HTTPS_PROXY;

    private CloseableHttpClient httpClient;
    private HttpHost target;
    private RequestConfig config;

    @PostConstruct
    void initialiase() throws Exception {

        Log.debug("SNYK_TOKEN:" + SNYK_TOKEN);
        if (SNYK_TOKEN == null || SNYK_TOKEN.trim().length() == 0) {
            Log.info("SNYK_TOKEN not set, see https://quarkus.io/guides/config-reference");
            throw new Exception("SNYK_TOKEN not set, see https://quarkus.io/guides/config-reference");
        }

        Log.debug("SNYK_ORGID:" + SNYK_ORGID);
        if (SNYK_ORGID == null || SNYK_ORGID.trim().length() == 0) {
            Log.info("SNYK_ORGID not set, see https://quarkus.io/guides/config-reference");
            throw new Exception("SNYK_ORGID not set, see https://quarkus.io/guides/config-reference");
        }
        if (HTTPS_PROXY.isPresent()) {
            Log.debug("HTTPS_PROXY.isPresent");
            HttpHost proxy = new HttpHost(HTTPS_PROXY.get());
            config = RequestConfig.custom().setProxy(proxy).build();
            target = new HttpHost(SNYK_DOMAIN, 4443, "https");
        } else if (HTTP_PROXY.isPresent()) {
            Log.debug("HTTP_PROXY.isPresent");
            HttpHost proxy = new HttpHost(HTTP_PROXY.get());
            config = RequestConfig.custom().setProxy(proxy).build();
            target = new HttpHost(SNYK_DOMAIN, 4443, "https");
        }

        Log.debug("HTTP_PROXY config =" + config);
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

    private String constructSnykURL(PackageURL purl) throws ServiceException {
        String url = SNYK_DOMAIN + "/rest/orgs/" + SNYK_ORGID + "/packages/" + parsePurlToSnykUrlParam(purl)
                + SNYK_API_ENDPOINT;
        Log.debug(url);
        return url;
    }

    @CacheResult(cacheName = "findings-cache")
    public OSSFindings getFreshIntel(PackageURL purl) throws IOException, ServiceException {

        HttpGet request = new HttpGet(constructSnykURL(purl));
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "token " + SNYK_TOKEN);

        Log.info(request.getFirstHeader("Authorization"));

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
            Log.info("Snyk Status code " + response.getStatusLine().getStatusCode());
            Log.debug("Payload " + new String(response.getEntity().getContent().readAllBytes()));
            throw new ServiceException(response.getStatusLine().getStatusCode());
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            OSSFindings findings = objectMapper.readValue(response.getEntity().getContent(), OSSFindings.class);
            return findings;
        } finally {
            response.close();
        }
    }

    public Response getFreshIntelRaw(PackageURL purl) throws IOException, ServiceException {

        HttpGet request = new HttpGet(constructSnykURL(purl));
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", "token: " + SNYK_TOKEN);
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

    private static String parsePurlToSnykUrlParam(PackageURL purl) {

        String url = purl.getScheme() + "%3A" + purl.getType() + "%2f";
        if (purl.getNamespace() != null) {
            url = url + purl.getNamespace() + "%2f";
        }
        url = url + purl.getName() + "%40" + purl.getVersion();
        return url;
    }

    public static boolean isCapable(final PackageURL purl) {
        return purl != null
                && purl.getScheme() != null
                && purl.getType() != null
                && purl.getName() != null
                && purl.getVersion() != null;
    }

}