package com.btpltech.bankingbot.Activity;

/**
 * Created by Rajmendra on 4/21/2016.
 */
public class User {
    public User(String userName, String password, String email, String mobile) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName,password,email,mobile;
}

