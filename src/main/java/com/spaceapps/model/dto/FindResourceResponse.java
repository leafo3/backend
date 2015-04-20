package com.spaceapps.model.dto;

/**
 * Created by Chuy on 4/11/2015.
 */
public class FindResourceResponse<T> {
    private T data;
    private boolean success;
    private String message;

    public FindResourceResponse(T data, boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public FindResourceResponse() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean getSuccess() {
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
