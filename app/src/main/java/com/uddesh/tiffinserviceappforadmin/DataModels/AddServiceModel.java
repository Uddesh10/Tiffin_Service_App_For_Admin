package com.uddesh.tiffinserviceappforadmin.DataModels;

public class AddServiceModel {

    private final String foodimage;
    private final String servicename;
    private final String description;
    private final int price;
    private final String username;
    private final boolean active;

    public AddServiceModel(String foodimage, String servicename, String description, int price, String username, boolean active) {
        this.foodimage = foodimage;
        this.servicename = servicename;
        this.description = description;
        this.price = price;
        this.username = username;
        this.active = active;
    }

    public String getFoodimage() {
        return foodimage;
    }

    public String getServicename() {
        return servicename;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return active;
    }
}

