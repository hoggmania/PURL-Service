
package snyk.model;

import java.util.HashMap;
import java.util.Map;

@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
@io.quarkus.runtime.annotations.RegisterForReflection
public class Meta {

    private Package _package;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Meta() {
    }

    public Package getPackage() {
        return _package;
    }

    public void setPackage(Package _package) {
        this._package = _package;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
