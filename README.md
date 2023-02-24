[![OpenSSF
Scorecard](https://api.securityscorecards.dev/projects/github.com/hoggmania/PURL-Service/badge)](https://api.securityscorecards.dev/projects/github.com/hoggmania/PURL-Service)

# AST-SBOM-Service Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/AST-SBOM-Service-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides







https://repo1.maven.org/maven2/com/github/lalyos/jfiglet/0.0.9/

Set FIGLET_VERSION=0.0.9

curl -o jfiglet.jar http://central.maven.org/maven2/com/github/lalyos/jfiglet/%FIGLET_VERSION%/jfiglet-%FIGLET_VERSION%.jar
java -jar jfiglet.jar "text to convert"




    public OSSFindings getFreshIntel(PackageURL purl) throws NoContentException {
        final String url = SNYK_API_BASE_URL + parsePurlToSnykUrlParam(purl) + SNYK_API_ENDPOINT;
        System.out.println(url);
        return null;
    }



       /* 
          Uni<JsonObject> resp  = client.getAbs(url).putHeader("Authorization", SNYK_API_TOKEN).send()
          .onItem().transform(HttpResponse::bodyAsJsonObject);
          resp.
        throw new NoContentException("Nothing returned");
    private String run(PackageURL purl) {


        final String snykUrl = SNYK_API_BASE_URL + parsePurlToSnykUrlParam(purl) + SNYK_API_ENDPOINT;
        System.out.println(snykUrl);
        // Send a GET request
client
  .get(8080, "myserver.mycompany.com", "/some-uri")
  .send()
  .onSuccess(response -> System.out
    .println("Received response with status code" + response.statusCode()))
  .onFailure(err ->
    System.out.println("Something went wrong " + err.getMessage()));

// Send a HEAD request
client
  .head(8080, "myserver.mycompany.com", "/some-uri")
  .send()
  .onSuccess(response -> System.out
    .println("Received response with status code" + response.statusCode()))
  .onFailure(err ->
    System.out.println("Something went wrong " + err.getMessage()));

            return client.getAbs(snykUrl).putHeader("Authorization", SNYK_API_TOKEN)
                .send()
                .onItem().transform(resp -> {
                    if (resp.statusCode() == 200) {
                        return resp.bodyAsJsonObject().toString();
                    } else {
                        throw new RuntimeException("Error code = "+resp.statusCode());
                    }
                });

                Uni<HttpResponse<Buffer>> response = client.getAbs(snykUrl).putHeader("Authorization", SNYK_API_TOKEN).send();

                response.onItem().transform(resp -> {
                    if (resp.statusCode() == 200) {
                        return resp.bodyAsJsonObject().toString();
                    } else {
                        throw new RuntimeException("Error code = "+resp.statusCode());
                    }
                });
                throw new RuntimeException("Unknown Error code");
                 


        Uni<JsonObject> response = client.getAbs(snykUrl).putHeader("Authorization", SNYK_API_TOKEN).send().onItem().transform(HttpResponse::bodyAsJsonObject);
        //.bearerTokenAuthentication(SNYK_API_TOKEN);
        response
        if (response. == 200) {
            return resp.getBody().toString();
        } else {
            throw new RuntimeException("Error code = "+resp.getStatus());
        }
        return response.onItem().toString();
        /*
        // Configure with authentication:
        Unirest.config()

        
            .useSystemProperties(true)
            //.proxy(HTTP_PROXY, Integer.parseInt(HTTP_PROXY_PORT), HTTP_PROXY_USERNAME, HTTP_PROXY_PWD)
            .connectTimeout(10000)
            .verifySsl(false);

            Map<String, String> headers = new HashMap<>();
            headers.put("accept", "application/json");
            headers.put("Authorization", SNYK_API_TOKEN);
            
        final GetRequest request = Unirest.get(snykUrl).headers(headers);


        final HttpResponse<String> resp = request.asString();
        
        //final HttpResponse<JsonNode> resp = request.asJson();
        if (resp.getStatus() == 200) {
            return resp.getBody().toString();
        } else {
            throw new RuntimeException("Error code = "+resp.getStatus());
        }
    
    }
    
    */
               
  /*
    private WebClient client;    

    @Inject Vertx vertx;

    @PostConstruct
    void initialize() {
        //this.client = WebClient.create(vertx, new WebClientOptions().setDefaultHost("fruityvice.com").setDefaultPort(443).setSsl(true).setTrustAll(true));

        /*

        import io.vertx.core.net.ProxyOptions;
        import io.vertx.core.net.ProxyType;

        ProxyOptions poptions = new ProxyOptions();
        poptions.setType(ProxyType.HTTP);
        poptions.setHost(PROXY);
        poptions.setPort(Integer.parseInt(PROXY_PORT));
        poptions.setUsername(PROXY_USERNAME);
        poptions.setPassword(PROXY_PWD);
        * /

        //KeyCertOptions koptions = KeyCertOptions.wrap(null);
        WebClientOptions woptions = new WebClientOptions().setKeepAlive(false).setFollowRedirects(true).setTrustAll(true).setSsl(true);
        this.client = WebClient.create(vertx, woptions);
    }

   
    @GET
    @Path("/web2")
    public Uni<JsonObject> retrieveDataFromWikipedia1() {
        String url = "https://en.wikipedia.org/w/api.php?action=parse&page=Quarkus&format=json&prop=langlinks";
        return client.getAbs(url).send().onItem().transform(io.vertx.mutiny.ext.web.client.HttpResponse::bodyAsJsonObject);
                
    }



    private static final String URL = "https://en.wikipedia.org/w/api.php?action=parse&page=Quarkus&format=json&prop=langlinks";


    @GET
    @Path("/web")
    public Uni<JsonArray> retrieveDataFromWikipedia2() {                     
        return client.getAbs(URL).send()                                    
                .onItem().transform(io.vertx.mutiny.ext.web.client.HttpResponse::bodyAsJsonObject)         
                .onItem().transform(json -> json.getJsonObject("parse").getJsonArray("langlinks"));
    }

    */

        
    




AuthScope
