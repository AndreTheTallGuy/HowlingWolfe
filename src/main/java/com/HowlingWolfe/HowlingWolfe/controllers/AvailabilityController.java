package com.HowlingWolfe.HowlingWolfe.controllers;

import com.HowlingWolfe.HowlingWolfe.models.Availability;
import com.HowlingWolfe.HowlingWolfe.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/tripavail")
public class AvailabilityController {

    private AvailabilityService availabilityService;

    @Autowired
    public AvailabilityController(AvailabilityService availabilityService) { this.availabilityService =
            availabilityService; }

    @GetMapping(path = "/{subType}")
    public ResponseEntity<Availability> getAvailByTripType(@PathVariable String subType){
        Availability response = availabilityService.getBySubType(subType);
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String> updateTripAvail(@RequestBody Availability availObj){
        System.out.println(availObj.getDates());
        String response = availabilityService.update(availObj);
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
