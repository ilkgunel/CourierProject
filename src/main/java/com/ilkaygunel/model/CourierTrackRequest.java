package com.ilkaygunel.model;

public class CourierTrackRequest {
    private Long courierId;
    private Long trackingRequestTime;
    private Double longitude;
    private Double latitude;

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public Long getTrackingRequestTime() {
        return trackingRequestTime;
    }

    public void setTrackingRequestTime(Long trackingRequestTime) {
        this.trackingRequestTime = trackingRequestTime;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
