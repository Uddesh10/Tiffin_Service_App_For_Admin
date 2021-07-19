package com.uddesh.tiffinserviceappforadmin.DataModels;

public class UserDetailsModel {
    private final String fullname;
    private final String servicename;
    private final String contactno;
    private final double longitude;
    private final double latitude;
    private final String location;


    public UserDetailsModel(String fullname, String servicename, String contactno, double longitude, double latitude, String location) {
        this.fullname = fullname;
        this.servicename = servicename;
        this.contactno = contactno;
        this.longitude = longitude;
        this.latitude = latitude;
        this.location = location;
    }

    public String getFullname() {
        return fullname;
    }

    public String getServicename() {
        return servicename;
    }

    public String getContactno() {
        return contactno;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getLocation() {
        return location;
    }
}

