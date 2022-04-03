package com.ilkaygunel.util;

import org.springframework.stereotype.Component;

@Component
public class GeoUtils {

    /**
     * Returns calculated distance according to incoming longitudes and latitudes in meter format
     */
    public double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        latitude2 = Math.toRadians(latitude2);
        longitude2 = Math.toRadians(longitude2);


        double storeLatitude = Math.toRadians(latitude1);
        double storeLongitude = Math.toRadians(longitude1);

        double earthRadius = 6371.01; //Kilometers
        double distance = earthRadius * Math.acos(Math.sin(latitude2) * Math.sin(storeLatitude) + Math.cos(latitude2) * Math.cos(storeLatitude)
                * Math.cos(longitude2 - storeLongitude));

        return distance * 1000;

    }

}
