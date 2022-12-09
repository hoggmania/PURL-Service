package org.hoggmania.services.model;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.github.packageurl.PackageURL;


public class Repo{


    public enum PolicyFailureReason {
        VULN_SEVERITY("vuln_severity"),
        AGE("age"),
        MALICIOUS("malicious"),
        METADATA("metadata"),
        UNDER_INVESTIGATION("under_investigation"),
        USE_ASSURED_SUPPLIER("use_assured_supplier");

        public static List<PolicyFailureReason> setToList(EnumSet<PolicyFailureReason> resps) {
            return new ArrayList<PolicyFailureReason>(resps);
        }

        public static List<PolicyFailureReason> toList(PolicyFailureReason resps) {
            List<PolicyFailureReason> l = new ArrayList<PolicyFailureReason>();
            if (resps != null) l.add(resps);
            return l;
        }

        public String getValue() {
            return value;
        }

        private String value;

        private PolicyFailureReason(String value) {
            this.value = value;
        }
    }


    
    private String timestamp;
    private List<PolicyFailureReason> policyFailureReason = new ArrayList<PolicyFailureReason>();
    private List<Issue> issues = new ArrayList<Issue>();
    private PackageURL purl;
    private Long createdAt;
    private Long refreshedAt;
    private boolean isDefault = false;

/*
    private ScorecardV2 scorecard;

    public ScorecardV2 getScorecard() {
        return scorecard;
    }

    public void setScorecard(ScorecardV2 scorecard) {
        this.scorecard = scorecard;
    }
*/    

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getRefreshedAt() {
        return refreshedAt;
    }

    public void setRefreshedAt(Long refreshedAt) {
        this.refreshedAt = refreshedAt;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Repo() {
    } 
    
    public Repo(String timestamp, PackageURL purl) {
        this.timestamp = timestamp;
        this.purl = purl;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<PolicyFailureReason> getPolicyFailureReason() {
        return policyFailureReason;
    }

    public void setPolicyFailureReason(List<PolicyFailureReason> policyFailureReason) {
        this.policyFailureReason = policyFailureReason;
    }

    public PackageURL getPurl() {
        return purl;
    }

    public void setPurl(PackageURL purl) {
        this.purl = purl;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

}
