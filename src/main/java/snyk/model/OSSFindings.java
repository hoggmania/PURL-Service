
package snyk.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
@io.quarkus.runtime.annotations.RegisterForReflection
public class OSSFindings {

    private Jsonapi jsonapi;
    private List<Datum> data = null;
    private Links links;
    private Meta meta;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    public static OSSFindings to(Jsonapi jsonapi, List<Datum> data, Links links, Meta meta,Map<String, Object> additionalProperties) {
        return new OSSFindings(jsonapi, data, links, meta, additionalProperties);
    }

    public OSSFindings(Jsonapi jsonapi, List<Datum> data, Links links, Meta meta,
            Map<String, Object> additionalProperties) {
        this.jsonapi = jsonapi;
        this.data = data;
        this.links = links;
        this.meta = meta;
        this.additionalProperties = additionalProperties;
    }

    public OSSFindings() {
    }

    public Jsonapi getJsonapi() {
        return jsonapi;
    }

    public void setJsonapi(Jsonapi jsonapi) {
        this.jsonapi = jsonapi;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
