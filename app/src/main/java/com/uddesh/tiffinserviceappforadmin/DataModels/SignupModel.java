package com.uddesh.tiffinserviceappforadmin.DataModels;

public class SignupModel {
    private final String username;
    private final String password;
    private final String mobileno;

    public SignupModel(String username , String password, String mobileno) {
        this.username = username;
        this.password = password;
        this.mobileno = mobileno;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileno() {
        return mobileno;
    }
}
