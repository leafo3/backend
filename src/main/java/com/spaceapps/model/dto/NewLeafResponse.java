package com.spaceapps.model.dto;

/**
 * Created by Alberto Rubalcaba on 4/19/2015.
 */
public class NewLeafResponse {
    private String id;
    private boolean success;
    private int damageClass;


    public NewLeafResponse(String id, boolean success, int damageClass) {
        this.id = id;
        this.success = success;
        this.damageClass = damageClass;
    }

    public NewLeafResponse() {
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

    public int getDamageClass() {
        return damageClass;
    }

    public void setDamageClass(int damageClass) {
        this.damageClass = damageClass;
    }
}
