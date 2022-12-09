package org.hoggmania.services.model;

public class Classification {

    private String id;
    private String source;
    
    public Classification() {
    }

    public Classification(String id, String source) {
        this.id = id;
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
