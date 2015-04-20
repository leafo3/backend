package com.spaceapps.model.dto;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public class LoginResponse {
    private String id;
    private String message;

    public LoginResponse(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public LoginResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
