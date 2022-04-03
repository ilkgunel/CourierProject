package com.ilkaygunel.model;

import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("name")
    private String storeName;

    @SerializedName("lat")
    private double storeLatitude;

    @SerializedName("lng")
    private double storeLongitude;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLatitude(double storeLatitude) {
        this.storeLatitude = storeLatitude;
    }

    public double getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreLongitude(double storeLongitude) {
        this.storeLongitude = storeLongitude;
    }
}
