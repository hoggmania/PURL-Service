
package dev.deps.schema;

import java.util.HashMap;
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
    "type",
    "name",
    "observedAt",
    "issues",
    "forks",
    "stars",
    "description",
    "license",
    "displayName",
    "link",
    "scorecardV2"
})
@Generated("jsonschema2pojo")
public class Project {

    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("observedAt")
    private Long observedAt;
    @JsonProperty("issues")
    private Long issues;
    @JsonProperty("forks")
    private Long forks;
    @JsonProperty("stars")
    private Long stars;
    @JsonProperty("description")
    private String description;
    @JsonProperty("license")
    private String license;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("link")
    private String link;
    @JsonProperty("scorecardV2")
    private ScorecardV2 scorecardV2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Project() {
    }

    /**
     * 
     * @param forks
     * @param license
     * @param observedAt
     * @param displayName
     * @param name
     * @param link
     * @param scorecardV2
     * @param description
     * @param stars
     * @param type
     * @param issues
     */
    public Project(String type, String name, Long observedAt, Long issues, Long forks, Long stars, String description, String license, String displayName, String link, ScorecardV2 scorecardV2) {
        super();
        this.type = type;
        this.name = name;
        this.observedAt = observedAt;
        this.issues = issues;
        this.forks = forks;
        this.stars = stars;
        this.description = description;
        this.license = license;
        this.displayName = displayName;
        this.link = link;
        this.scorecardV2 = scorecardV2;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("observedAt")
    public Long getObservedAt() {
        return observedAt;
    }

    @JsonProperty("observedAt")
    public void setObservedAt(Long observedAt) {
        this.observedAt = observedAt;
    }

    @JsonProperty("issues")
    public Long getIssues() {
        return issues;
    }

    @JsonProperty("issues")
    public void setIssues(Long issues) {
        this.issues = issues;
    }

    @JsonProperty("forks")
    public Long getForks() {
        return forks;
    }

    @JsonProperty("forks")
    public void setForks(Long forks) {
        this.forks = forks;
    }

    @JsonProperty("stars")
    public Long getStars() {
        return stars;
    }

    @JsonProperty("stars")
    public void setStars(Long stars) {
        this.stars = stars;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    @JsonProperty("license")
    public void setLicense(String license) {
        this.license = license;
    }

    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("scorecardV2")
    public ScorecardV2 getScorecardV2() {
        return scorecardV2;
    }

    @JsonProperty("scorecardV2")
    public void setScorecardV2(ScorecardV2 scorecardV2) {
        this.scorecardV2 = scorecardV2;
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
