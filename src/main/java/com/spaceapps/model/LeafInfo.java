package com.spaceapps.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

/**
 * Created by Alberto Rubalcava  on 4/11/2015.
 */
public class LeafInfo extends BaseEntity{
    private String leafPicUrl;
    private String damagePercent;
    private String location;
    private String publishDate;
    private String title;
    private String comment;
    private String email;
    private int damageClass;

    public LeafInfo(String title, String comment,
                    String leafPicUrl, String damagePercent,
                    String location, String publishDate, String email,
                    int damageClass) {
        this.leafPicUrl = leafPicUrl;
        this.damagePercent = damagePercent;
        this.location = location;
        this.publishDate = publishDate;
        this.title = title;
        this.comment = comment;
        this.email = email;
        this.damageClass = damageClass;
    }

    public LeafInfo() {
    }

    public String getLeafPicUrl() {
        return leafPicUrl;
    }

    public void setLeafPicUrl(String leafPicUrl) {
        this.leafPicUrl = leafPicUrl;
    }

    public String getDamagePercent() {
        return damagePercent;
    }

    public void setDamagePercent(String damagePercent) {
        this.damagePercent = damagePercent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDamageClass() {
        return damageClass;
    }

    public void setDamageClass(int damageClass) {
        this.damageClass = damageClass;
    }
}
