package com.kata.user.model;

import java.util.Map;

/**
 * This class represent a generic error response to be returned by {@link com.kata.user.web.exception.ApiExceptionHandler} when an error occurred in the application.
 */
public class ErrorResponse {

    private String message;

    private String description;

    private Map<String, String> metaData;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, String description) {
        this.message = message;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
