package com.uddesh.tiffinserviceappforadmin.DataModels;

public class GetServiceModel {
    private final int id;
    private final String servicename;
    private final boolean active;
    private final int subscribed;

    public GetServiceModel(int id, String servicename, boolean active, int subscribed) {
        this.id = id;
        this.servicename = servicename;
        this.active = active;
        this.subscribed = subscribed;
    }

    public int getId() {
        return id;
    }

    public String getServicename() {
        return servicename;
    }

    public boolean isActive() {
        return active;
    }

    public int getSubscribed() {
        return subscribed;
    }
}
