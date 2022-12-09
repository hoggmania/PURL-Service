package org.hoggmania.services.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;;


@JsonInclude(Include.NON_NULL)
public class Issue {

    private String id;
    private String type;
    private String createdAt;
    private String updatedAt;

    
    private List<Classification> classification;
    private List<VulnScore> scores;
    private List<Analysis> analysis;

    public Issue(String id, String type, String createdAt, String updatedAt, List<Classification> classification,
            List<VulnScore> scores, List<Analysis> analysis) {
        this.id = id;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.classification = classification;
        this.scores = scores;
        this.analysis = analysis;
    }

    public Issue() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Classification> getClassification() {
        return classification;
    }

    public void setClassification(List<Classification> classification) {
        this.classification = classification;
    }

    public List<VulnScore> getScores() {
        return scores;
    }

    public void setScores(List<VulnScore> scores) {
        this.scores = scores;
    }

    public List<Analysis> getAnalysis() {
        return analysis;
    }

    public void setAnalysis(List<Analysis> analysis) {
        this.analysis = analysis;
    }



}
