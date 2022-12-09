
package snyk.model;

import java.util.HashMap;
import java.util.Map;

@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
@io.quarkus.runtime.annotations.RegisterForReflection
public class Details {

    private String upgradePackage;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    
    public Details() {
    }

    public String getUpgradePackage() {
        return upgradePackage;
    }

    public void setUpgradePackage(String upgradePackage) {
        this.upgradePackage = upgradePackage;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
