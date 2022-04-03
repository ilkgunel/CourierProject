package com.ilkaygunel.singleton;

import com.ilkaygunel.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CourierOrdersSingleton {

    private CourierOrdersSingleton() {

    }

    private static ConcurrentHashMap<Long, List<Order>> courierOrders; //Long -> Courier Id. List<Order> -> OrderList of courier

    public static ConcurrentHashMap<Long, List<Order>> getSingletonCourierOrders() {
        synchronized (CourierOrdersSingleton.class) {
            if (courierOrders == null) {
                courierOrders = populateInitialMap();
            }
        }
        return courierOrders;
    }

    /**
     * This method populates the courierOrders map with initial order values.
     */
    private static ConcurrentHashMap<Long, List<Order>> populateInitialMap() {
        Order order1ForCourier1 = new Order();
        order1ForCourier1.setOrderLatitude(41.047409);
        order1ForCourier1.setOrderLongitude(28.935539);
        order1ForCourier1.setOrderStoreLatitude(40.9923307);
        order1ForCourier1.setOrderStoreLongitude(29.1244229);

        Order order2ForCourier1 = new Order();
        order2ForCourier1.setOrderLatitude(41.112186);
        order2ForCourier1.setOrderLongitude(29.019964);
        order2ForCourier1.setOrderStoreLatitude(40.986106);
        order2ForCourier1.setOrderStoreLongitude(29.1161293);

        List<Order> orderListForCourier1 = new ArrayList<>();
        orderListForCourier1.add(order1ForCourier1);
        orderListForCourier1.add(order2ForCourier1);

        ConcurrentHashMap<Long, List<Order>> courierOrdersMap = new ConcurrentHashMap<>();
        courierOrdersMap.put(1l, orderListForCourier1);

        return courierOrdersMap;
    }

}
