
package dev.deps.schema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "package",
    "owners",
    "version",
    "defaultVersion"
})
@Generated("jsonschema2pojo")
public class VersionSummary {

    @JsonProperty("package")
    private Package _package;
    @JsonProperty("owners")
    private List<Object> owners = null;
    @JsonProperty("version")
    private Version version;
    @JsonProperty("defaultVersion")
    private String defaultVersion;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public VersionSummary() {
    }

    /**
     * 
     * @param _package
     * @param owners
     * @param version
     * @param defaultVersion
     */
    public VersionSummary(Package _package, List<Object> owners, Version version, String defaultVersion) {
        super();
        this._package = _package;
        this.owners = owners;
        this.version = version;
        this.defaultVersion = defaultVersion;
    }

    @JsonProperty("package")
    public Package getPackage() {
        return _package;
    }

    @JsonProperty("package")
    public void setPackage(Package _package) {
        this._package = _package;
    }

    @JsonProperty("owners")
    public List<Object> getOwners() {
        return owners;
    }

    @JsonProperty("owners")
    public void setOwners(List<Object> owners) {
        this.owners = owners;
    }

    @JsonProperty("version")
    public Version getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Version version) {
        this.version = version;
    }

    @JsonProperty("defaultVersion")
    public String getDefaultVersion() {
        return defaultVersion;
    }

    @JsonProperty("defaultVersion")
    public void setDefaultVersion(String defaultVersion) {
        this.defaultVersion = defaultVersion;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
