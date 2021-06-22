package com.HowlingWolfe.HowlingWolfe.controllers;

import com.HowlingWolfe.HowlingWolfe.services.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/boats")
public class BoatContoller {

    BoatService boatService;

    @Autowired
    BoatContoller(BoatService boatService){this.boatService = boatService;}

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deleteBoatById(@PathVariable String id){
        String response = boatService.deleteBoat(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
