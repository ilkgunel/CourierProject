package com.ilkaygunel.service;

import com.ilkaygunel.model.Order;
import com.ilkaygunel.singleton.CourierOrdersSingleton;
import com.ilkaygunel.util.GeoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CourierTravellingCalculationService {

    private GeoUtils geoUtils;

    @Autowired
    public CourierTravellingCalculationService(GeoUtils geoUtils) {
        this.geoUtils = geoUtils;
    }

    /**
     * Calculates total travelling distance of courier according to orders and return in kilometer format!
     */
    public Double getTotalTravelDistance(Long courierId) {
        AtomicReference<Double> totalTravellingDistance = new AtomicReference<Double>(0.0);
        List<Order> orderListOfCourier = CourierOrdersSingleton.getSingletonCourierOrders().entrySet().stream()
                .filter(entry -> courierId.equals(entry.getKey()))
                .map(entry -> entry.getValue())
                .flatMap(List::stream)
                .collect(Collectors.toList());

        orderListOfCourier.stream().forEach(order -> {
            double distance = geoUtils.calculateDistance(order.getOrderLatitude(), order.getOrderLongitude(), order.getOrderStoreLatitude(), order.getOrderStoreLongitude());
            totalTravellingDistance.set(totalTravellingDistance.get() + distance);
        });


        return totalTravellingDistance.get() / 1000;
    }

}
