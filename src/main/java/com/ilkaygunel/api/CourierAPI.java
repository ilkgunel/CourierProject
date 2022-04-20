package com.ilkaygunel.api;

import com.ilkaygunel.model.CourierTrackRequest;
import com.ilkaygunel.service.CourierTrackService;
import com.ilkaygunel.service.CourierTravellingCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
public class CourierAPI {

    private CourierTrackService courierTrackService;
    private CourierTravellingCalculationService courierTravellingCalculationService;

    @Autowired
    public CourierAPI(CourierTrackService courierTrackService, CourierTravellingCalculationService courierTravellingCalculationService) {
        this.courierTrackService = courierTrackService;
        this.courierTravellingCalculationService = courierTravellingCalculationService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/couriers/track")
    public ResponseEntity<String> trackCourier(@RequestBody CourierTrackRequest courierTrackRequest) throws FileNotFoundException {
        courierTrackService.checkAndLogCourierTrackRequest(courierTrackRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/couriers/travellingDistance/{courierId}")
    public ResponseEntity<Double> travellingDistance(@PathVariable Long courierId) {
        Double totalTravellingDistanceOfCourier = courierTravellingCalculationService.getTotalTravelDistance(courierId);
        return new ResponseEntity<Double>(totalTravellingDistanceOfCourier, HttpStatus.OK);
    }

}
