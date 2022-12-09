package org.hoggmania.services.model;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class VulnScore {
    /**
     * A common method for all enums since they can't have another base class
     * 
     * @param <T>    Enum type
     * @param c      enum type. All enums must be all caps.
     * @param string case insensitive
     * @return corresponding enum, or null
     */
    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if (c != null && string != null) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch (IllegalArgumentException ex) {
            }
        }
        return null;
    }


    public enum Severity {
        LOW("low"),
        MEDIUM("medium"),
        HIGH("high"),
        CRITICAL("critical");

        public static List<Severity> setToList(EnumSet<Severity> resps) {
            return new ArrayList<Severity>(resps);
        }

        public String getValue() {
            return value;
        }

        private String value;

        private Severity(String value) {
            this.value = value;
        }

        public static Severity fromString(String name) {
            return getEnumFromString(Severity.class, name);
        }

    }

    private String source;
    private Severity level;
    private Double score;
    private String vector;

    public VulnScore() {
    }

    public VulnScore(String source, Severity level, Double score, String vector) {
        this.source = source;
        this.level = level;
        this.score = score;
        this.vector = vector;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Severity getLevel() {
        return level;
    }

    public void setLevel(Severity level) {
        this.level = level;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getVector() {
        return vector;
    }

    public void setVector(String vector) {
        this.vector = vector;
    }

}
