package org.hoggmania.services.model;

public class ServiceException extends Exception{

    public static int NOT_IMPLEMENTED = 501; //HTTP Status code 501 Not Implemented
    
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ServiceException(int statusCode) {
        this.statusCode = statusCode;
    }



}
