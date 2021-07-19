package com.uddesh.tiffinserviceappforadmin.DataModels;

public class AdminLocationModel {
    private final String location;
    private final double latitude;
    private final double longitude;
    private final String username;

    public AdminLocationModel(String location, double latitude, double longitude, String username) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getUsername() {
        return username;
    }
}
