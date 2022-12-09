package org.hoggmania.services.model;

public class Data {
    private String bom;//base64 encoded
    private String scorecard;//base64 encoded
    private String findings;
    
    
    public Data() {
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public Data(String bom, String scorecard, String findings) {
        this.bom = bom;
        this.scorecard = scorecard;
        this.findings = findings;
    }
    
    public String getBom() {
        return bom;
    }

    public void setBom(String bom) {
        this.bom = bom;
    }

    public String getScorecard() {
        return scorecard;
    }

    public void setScorecard(String scorecard) {
        this.scorecard = scorecard;
    }
}
