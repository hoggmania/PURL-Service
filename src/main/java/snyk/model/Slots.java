
package snyk.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
@io.quarkus.runtime.annotations.RegisterForReflection
public class Slots {

    private String disclosureTime;
    private String exploit;
    private String publicationTime;
    private List<Reference> references = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Slots() {
    }

    public String getDisclosureTime() {
        return disclosureTime;
    }

    public void setDisclosureTime(String disclosureTime) {
        this.disclosureTime = disclosureTime;
    }

    public String getExploit() {
        return exploit;
    }

    public void setExploit(String exploit) {
        this.exploit = exploit;
    }

    public String getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(String publicationTime) {
        this.publicationTime = publicationTime;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
