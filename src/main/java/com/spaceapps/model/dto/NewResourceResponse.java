package com.spaceapps.model.dto;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public class NewResourceResponse {
    private String id;
    private boolean success;
    private String message;


    public NewResourceResponse(String id, boolean success, String message) {
        this.id = id;
        this.success = success;
        this.message = message;
    }

    public NewResourceResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
