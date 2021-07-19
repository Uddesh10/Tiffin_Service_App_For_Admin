package com.uddesh.tiffinserviceappforadmin.DataModels;

public class LoggedInDataModel {
    private final String token;
    private final String mobileno;

    public LoggedInDataModel(String token, String mobileno) {
        this.token = token;
        this.mobileno = mobileno;
    }

    public String getToken() {
        return token;
    }

    public String getMobileno() {
        return mobileno;
    }
}
