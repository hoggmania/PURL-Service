package org.hoggmania.services.model;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


public class Analysis {

    public enum State {
        IN_TRIAGE("in_triage"),
        NOT_AFFECTED("not_affected"),
        RESOLVED("resolved"),
        EXPLOITABLE("exploitable"),
        AFFECTED("affected");

        public String getValue() {
            return value;
        }

        private String value;

        private State(String value) {
            this.value = value;
        }
    };



    public enum VendorResponse {
        WILL_NOT_FIX("will_not_fix"),
        UPGRADE("update"),
        RESOLVED("resolved");
       

        public static List<VendorResponse> set_wnf_upgrade =   VendorResponse.setToList(EnumSet.of(WILL_NOT_FIX, UPGRADE));
        public static List<VendorResponse> set_resolved =   VendorResponse.setToList(EnumSet.of(RESOLVED));
        

        public static List<VendorResponse> setToList(EnumSet<VendorResponse> resps) {
            return  new ArrayList<VendorResponse>(resps);
        }

        public String getValue() {
            return value;
        }

        private String value;


        private VendorResponse(String value) {
            this.value = value;
        }

    }

    private State state;
    private String justification;
    private List<VendorResponse> response;
    private String detail;

    
    public Analysis() {
    }


    public Analysis(State state, String justifiaction, List<VendorResponse> response, String detail) {
        this.state = state;
        this.justification = justifiaction;
        this.response = response;
        this.detail = detail;
    }


    public State getState() {
        return state;
    }


    public void setState(State state) {
        this.state = state;
    }


    public String getJustification() {
        return justification;
    }


    public void setJustification(String justifiaction) {
        this.justification = justifiaction;
    }


    public List<VendorResponse> getResponse() {
        return response;
    }


    public void setResponse(List<VendorResponse> response) {
        this.response = response;
    }


    public String getDetail() {
        return detail;
    }


    public void setDetail(String detail) {
        this.detail = detail;
    }




}
