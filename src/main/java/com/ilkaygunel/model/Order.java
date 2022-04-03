package com.ilkaygunel.model;

public class Order {
    private double orderLatitude;
    private double orderLongitude;

    private double orderStoreLatitude;
    private double orderStoreLongitude;

    public double getOrderLatitude() {
        return orderLatitude;
    }

    public void setOrderLatitude(double orderLatitude) {
        this.orderLatitude = orderLatitude;
    }

    public double getOrderLongitude() {
        return orderLongitude;
    }

    public void setOrderLongitude(double orderLongitude) {
        this.orderLongitude = orderLongitude;
    }

    public double getOrderStoreLatitude() {
        return orderStoreLatitude;
    }

    public void setOrderStoreLatitude(double orderStoreLatitude) {
        this.orderStoreLatitude = orderStoreLatitude;
    }

    public double getOrderStoreLongitude() {
        return orderStoreLongitude;
    }

    public void setOrderStoreLongitude(double orderStoreLongitude) {
        this.orderStoreLongitude = orderStoreLongitude;
    }
}
