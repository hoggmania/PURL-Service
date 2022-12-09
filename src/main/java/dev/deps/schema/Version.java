
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
    "version",
    "symbolicVersions",
    "createdAt",
    "refreshedAt",
    "isDefault",
    "description",
    "licenses",
    "dependentCount",
    "dependentCountDirect",
    "dependentCountIndirect",
    "links",
    "projects",
    "advisories",
    "relatedPackages"
})
@Generated("jsonschema2pojo")
public class Version {

    @JsonProperty("version")
    private String version;
    @JsonProperty("symbolicVersions")
    private List<Object> symbolicVersions = null;
    @JsonProperty("createdAt")
    private Long createdAt;
    @JsonProperty("refreshedAt")
    private Long refreshedAt;
    @JsonProperty("isDefault")
    private Boolean isDefault = Boolean.FALSE;
    @JsonProperty("description")
    private String description;
    @JsonProperty("licenses")
    private List<String> licenses = null;
    @JsonProperty("dependentCount")
    private Long dependentCount;
    @JsonProperty("dependentCountDirect")
    private Long dependentCountDirect;
    @JsonProperty("dependentCountIndirect")
    private Long dependentCountIndirect;
    @JsonProperty("links")
    private Links links;
    @JsonProperty("projects")
    private List<Project> projects = null;
    @JsonProperty("advisories")
    private List<Object> advisories = null;
    @JsonProperty("relatedPackages")
    private RelatedPackages relatedPackages;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Version() {
    }

    /**
     * 
     * @param advisories
     * @param projects
     * @param relatedPackages
     * @param description
     * @param version
     * @param createdAt
     * @param isDefault
     * @param licenses
     * @param dependentCountDirect
     * @param dependentCountIndirect
     * @param symbolicVersions
     * @param refreshedAt
     * @param links
     * @param dependentCount
     */
    public Version(String version, List<Object> symbolicVersions, Long createdAt, Long refreshedAt, Boolean isDefault, String description, List<String> licenses, Long dependentCount, Long dependentCountDirect, Long dependentCountIndirect, Links links, List<Project> projects, List<Object> advisories, RelatedPackages relatedPackages) {
        super();
        this.version = version;
        this.symbolicVersions = symbolicVersions;
        this.createdAt = createdAt;
        this.refreshedAt = refreshedAt;
        this.isDefault = isDefault;
        this.description = description;
        this.licenses = licenses;
        this.dependentCount = dependentCount;
        this.dependentCountDirect = dependentCountDirect;
        this.dependentCountIndirect = dependentCountIndirect;
        this.links = links;
        this.projects = projects;
        this.advisories = advisories;
        this.relatedPackages = relatedPackages;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("symbolicVersions")
    public List<Object> getSymbolicVersions() {
        return symbolicVersions;
    }

    @JsonProperty("symbolicVersions")
    public void setSymbolicVersions(List<Object> symbolicVersions) {
        this.symbolicVersions = symbolicVersions;
    }

    @JsonProperty("createdAt")
    public Long getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("refreshedAt")
    public Long getRefreshedAt() {
        return refreshedAt;
    }

    @JsonProperty("refreshedAt")
    public void setRefreshedAt(Long refreshedAt) {
        this.refreshedAt = refreshedAt;
    }

    @JsonProperty("isDefault")
    public Boolean getIsDefault() {
        return isDefault;
    }

    @JsonProperty("isDefault")
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("licenses")
    public List<String> getLicenses() {
        return licenses;
    }

    @JsonProperty("licenses")
    public void setLicenses(List<String> licenses) {
        this.licenses = licenses;
    }

    @JsonProperty("dependentCount")
    public Long getDependentCount() {
        return dependentCount;
    }

    @JsonProperty("dependentCount")
    public void setDependentCount(Long dependentCount) {
        this.dependentCount = dependentCount;
    }

    @JsonProperty("dependentCountDirect")
    public Long getDependentCountDirect() {
        return dependentCountDirect;
    }

    @JsonProperty("dependentCountDirect")
    public void setDependentCountDirect(Long dependentCountDirect) {
        this.dependentCountDirect = dependentCountDirect;
    }

    @JsonProperty("dependentCountIndirect")
    public Long getDependentCountIndirect() {
        return dependentCountIndirect;
    }

    @JsonProperty("dependentCountIndirect")
    public void setDependentCountIndirect(Long dependentCountIndirect) {
        this.dependentCountIndirect = dependentCountIndirect;
    }

    @JsonProperty("links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonProperty("projects")
    public List<Project> getProjects() {
        return projects;
    }

    @JsonProperty("projects")
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @JsonProperty("advisories")
    public List<Object> getAdvisories() {
        return advisories;
    }

    @JsonProperty("advisories")
    public void setAdvisories(List<Object> advisories) {
        this.advisories = advisories;
    }

    @JsonProperty("relatedPackages")
    public RelatedPackages getRelatedPackages() {
        return relatedPackages;
    }

    @JsonProperty("relatedPackages")
    public void setRelatedPackages(RelatedPackages relatedPackages) {
        this.relatedPackages = relatedPackages;
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
