package com.spaceapps.model;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public class ValidationError {
    private String error;
    private String title;

    public ValidationError(String error, String title) {
        this.error = error;
        this.title = title;
    }

    public ValidationError() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
