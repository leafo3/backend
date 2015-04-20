package com.spaceapps.model;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public class User extends BaseEntity{

    private String nickname;
    private String email;
    private String password;
    private String location;
    private String interest;

    public User(String name, String email, String password,String nickname, String location, String interest){
        this.nickname = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.interest = interest;
    }

    public User() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
