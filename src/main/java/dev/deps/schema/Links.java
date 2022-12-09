
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
    "origins",
    "homepage",
    "issues",
    "repo"
})
@Generated("jsonschema2pojo")
public class Links {

    @JsonProperty("origins")
    private List<String> origins = null;
    @JsonProperty("homepage")
    private String homepage;
    @JsonProperty("issues")
    private String issues;
    @JsonProperty("repo")
    private String repo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Links() {
    }

    /**
     * 
     * @param repo
     * @param origins
     * @param issues
     * @param homepage
     */
    public Links(List<String> origins, String homepage, String issues, String repo) {
        super();
        this.origins = origins;
        this.homepage = homepage;
        this.issues = issues;
        this.repo = repo;
    }

    @JsonProperty("origins")
    public List<String> getOrigins() {
        return origins;
    }

    @JsonProperty("origins")
    public void setOrigins(List<String> origins) {
        this.origins = origins;
    }

    @JsonProperty("homepage")
    public String getHomepage() {
        return homepage;
    }

    @JsonProperty("homepage")
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @JsonProperty("issues")
    public String getIssues() {
        return issues;
    }

    @JsonProperty("issues")
    public void setIssues(String issues) {
        this.issues = issues;
    }

    @JsonProperty("repo")
    public String getRepo() {
        return repo;
    }

    @JsonProperty("repo")
    public void setRepo(String repo) {
        this.repo = repo;
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
