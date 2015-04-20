package com.spaceapps.model;

import java.util.UUID;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public abstract class BaseEntity {
    protected String id;

    public BaseEntity(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
