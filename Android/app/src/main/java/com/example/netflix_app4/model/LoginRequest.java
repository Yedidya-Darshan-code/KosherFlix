package com.example.netflix_app4.model;

public class LoginRequest {
    private final String userName;
    private final String password;

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}