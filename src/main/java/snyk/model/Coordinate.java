
package snyk.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
@io.quarkus.runtime.annotations.RegisterForReflection
public class Coordinate {

    private List<Remedy> remedies = null;
    private List<String> representation = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    
    public Coordinate() {
    }

    public List<Remedy> getRemedies() {
        return remedies;
    }

    public void setRemedies(List<Remedy> remedies) {
        this.remedies = remedies;
    }

    public List<String> getRepresentation() {
        return representation;
    }

    public void setRepresentation(List<String> representation) {
        this.representation = representation;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
