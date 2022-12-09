
package dev.deps.schema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "documentation",
    "score",
    "reason",
    "details"
})
public class Check {

    @JsonProperty("name")
    private String name;
    @JsonProperty("documentation")
    private Documentation documentation;
    @JsonProperty("score")
    private Long score;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("details")
    private List<String> details = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Check() {
    }

    /**
     * 
     * @param score
     * @param reason
     * @param documentation
     * @param name
     * @param details
     */
    public Check(String name, Documentation documentation, Long score, String reason, List<String> details) {
        super();
        this.name = name;
        this.documentation = documentation;
        this.score = score;
        this.reason = reason;
        this.details = details;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("documentation")
    public Documentation getDocumentation() {
        return documentation;
    }

    @JsonProperty("documentation")
    public void setDocumentation(Documentation documentation) {
        this.documentation = documentation;
    }

    @JsonProperty("score")
    public Long getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Long score) {
        this.score = score;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonProperty("details")
    public List<String> getDetails() {
        return details;
    }

    @JsonProperty("details")
    public void setDetails(List<String> details) {
        this.details = details;
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
