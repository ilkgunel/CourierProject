package com.ilkaygunel.singleton;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CourierEntrancesSingleton {

    private CourierEntrancesSingleton() {

    }

    private static ConcurrentHashMap<Long, List<HashMap<String, Long>>> courierEntrances; //Long -> Courier Id. String, Long -> Store Name and Entrance Time

    public static ConcurrentHashMap<Long, List<HashMap<String, Long>>> getSingletonCourierEntrances() {
        synchronized (CourierEntrancesSingleton.class) {
            if (courierEntrances == null) {
                courierEntrances = new ConcurrentHashMap<>();
            }
        }
        return courierEntrances;
    }

}
