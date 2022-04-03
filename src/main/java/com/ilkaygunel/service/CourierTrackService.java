package com.ilkaygunel.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.ilkaygunel.model.CourierTrackRequest;
import com.ilkaygunel.model.Store;
import com.ilkaygunel.singleton.CourierEntrancesSingleton;
import com.ilkaygunel.util.GeoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CourierTrackService {

    private static final Logger logger = LoggerFactory.getLogger(CourierTrackService.class);

    private GeoUtils geoUtils;

    @Autowired
    public CourierTrackService(GeoUtils geoUtils) {
        this.geoUtils = geoUtils;
    }

    public void checkAndLogCourierTrackRequest(CourierTrackRequest courierTrackRequest) throws FileNotFoundException {
        List<Store> storeList = getStoreListFromJsonFile();

        //MutableBoolean ongoing = MutableBoolean.of(true); Apache Commons Lang bağımlılıklar arasında varsa bu da kullanılabilir
        AtomicReference<Boolean> bool = new AtomicReference<>(true);
        storeList.stream().takeWhile(store -> bool.get().booleanValue()).forEach(store -> {
            double distance = geoUtils.calculateDistance(store.getStoreLatitude(), store.getStoreLongitude(), courierTrackRequest.getLatitude(), courierTrackRequest.getLongitude());
            logger.info("Distance of courier to store: {}, {}", distance, store.getStoreName());

            if (distance <= 100) {
                List<HashMap<String, Long>> courierEntrance = CourierEntrancesSingleton.getSingletonCourierEntrances().get(courierTrackRequest.getCourierId());
                //Long trackingRequestTime = Instant.now().toEpochMilli();
                Long trackingRequestTime = courierTrackRequest.getTrackingRequestTime();
                if (courierEntrance == null) { //Kurye'nin hiçbir mağazaya giriş kaydı yok! Sıfırdan oluşturup koyalım
                    addFirstEntranceToSingletonMap(store.getStoreName(), trackingRequestTime, courierTrackRequest.getCourierId());
                } else if (courierEntrance.stream().anyMatch(item -> item.keySet().contains(store.getStoreName()))) { //Kurye'nin başka mağazalara girişi kaydı var ama yaklaştığına var mı?
                    updateExistingStoreEntrance(courierEntrance, trackingRequestTime, store.getStoreName(), courierTrackRequest.getCourierId());
                } else {
                    addFirstStoreEntranceSingletonMap(store.getStoreName(), trackingRequestTime, courierTrackRequest.getCourierId(), courierEntrance);
                }
                logger.info("The courier {} entered radius of 100 meter of store:{}", courierTrackRequest.getCourierId(), store.getStoreName());
                bool.set(false);
            }
        });

    }

    private List<Store> getStoreListFromJsonFile() throws FileNotFoundException {
        List<Store> storeList = new ArrayList<>();
        FileReader fileReader = new FileReader("src/main/resources/stores.json");
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(fileReader).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            Store store = gson.fromJson(jsonArray.get(i), Store.class);
            storeList.add(store);
        }
        return storeList;
    }

    private void addFirstEntranceToSingletonMap(String storeName, Long trackingRequestTime, Long courierId) {
        HashMap<String, Long> storeEntranceTime = new HashMap<>();
        storeEntranceTime.put(storeName, trackingRequestTime);
        List<HashMap<String, Long>> storeEntranceTimeList = new ArrayList<>();
        storeEntranceTimeList.add(storeEntranceTime);
        CourierEntrancesSingleton.getSingletonCourierEntrances().put(courierId, storeEntranceTimeList);
    }

    private void addFirstStoreEntranceSingletonMap(String storeName, Long trackingRequestTime, Long courierId, List<HashMap<String, Long>> storeEntranceTimeList) {
        HashMap<String, Long> storeEntranceTime = new HashMap<>();
        storeEntranceTime.put(storeName, trackingRequestTime);
        storeEntranceTimeList.add(storeEntranceTime);
        CourierEntrancesSingleton.getSingletonCourierEntrances().put(courierId, storeEntranceTimeList);
    }

    private void updateExistingStoreEntrance(List<HashMap<String, Long>> courierEntrance, Long trackingRequestTime, String storeName, Long courierId) {
        //Varsa 1 dakika kontrolü yapalım ve mevcudu güncelleyelim
        AtomicReference<Long> entranceTime = new AtomicReference<>();
        courierEntrance.stream().forEach(item -> {
            entranceTime.set(item.entrySet().stream().filter(entry -> entry.getKey().equals(storeName)).findFirst().get().getValue());
        });
        logger.info("Tracing Request Time: {}", trackingRequestTime);
        logger.info("Entrance Time: {}", entranceTime.get());
        if ((trackingRequestTime - entranceTime.get()) <= 60000l) {
            logger.info("The courier entered to same store in 1 minute, this entrance is not being counted!");
            logger.info("The gap: {}", (trackingRequestTime - entranceTime.get()));
        } else {
            logger.info("The courier entered to store over 1 minute.");
            int index = courierEntrance.indexOf(courierEntrance.stream().filter(hashMap -> (hashMap.get(storeName) != null)).findFirst().get());
            HashMap<String, Long> newHashMap = new HashMap<>();
            newHashMap.put(storeName, trackingRequestTime);
            courierEntrance.remove(index);
            courierEntrance.add(index, newHashMap);
            CourierEntrancesSingleton.getSingletonCourierEntrances().put(courierId, courierEntrance);
        }
    }
}
