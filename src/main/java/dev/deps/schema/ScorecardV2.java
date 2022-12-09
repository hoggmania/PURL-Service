
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
    "date",
    "repo",
    "scorecard",
    "check",
    "metadata",
    "score"
})
@Generated("jsonschema2pojo")
public class ScorecardV2 {

    @JsonProperty("date")
    private String date;
    @JsonProperty("repo")
    private Repo repo;
    @JsonProperty("scorecard")
    private Scorecard scorecard;
    @JsonProperty("check")
    private List<Check> check = null;
    @JsonProperty("metadata")
    private List<Object> metadata = null;
    @JsonProperty("score")
    private Double score;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ScorecardV2() {
    }

    /**
     * 
     * @param date
     * @param score
     * @param metadata
     * @param repo
     * @param check
     * @param scorecard
     */
    public ScorecardV2(String date, Repo repo, Scorecard scorecard, List<Check> check, List<Object> metadata, Double score) {
        super();
        this.date = date;
        this.repo = repo;
        this.scorecard = scorecard;
        this.check = check;
        this.metadata = metadata;
        this.score = score;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("repo")
    public Repo getRepo() {
        return repo;
    }

    @JsonProperty("repo")
    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    @JsonProperty("scorecard")
    public Scorecard getScorecard() {
        return scorecard;
    }

    @JsonProperty("scorecard")
    public void setScorecard(Scorecard scorecard) {
        this.scorecard = scorecard;
    }

    @JsonProperty("check")
    public List<Check> getCheck() {
        return check;
    }

    @JsonProperty("check")
    public void setCheck(List<Check> check) {
        this.check = check;
    }

    @JsonProperty("metadata")
    public List<Object> getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(List<Object> metadata) {
        this.metadata = metadata;
    }

    @JsonProperty("score")
    public Double getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Double score) {
        this.score = score;
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
