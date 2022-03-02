package com.kata.user.model;

import java.util.Map;

public class ErrorResponse {

    private String message;

    private Map<String, String> metaData;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, Map<String, String> metaData) {
        this.message = message;
        this.metaData = metaData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public void setMetaData(Map<String, String> metaData) {
        this.metaData = metaData;
    }
}
