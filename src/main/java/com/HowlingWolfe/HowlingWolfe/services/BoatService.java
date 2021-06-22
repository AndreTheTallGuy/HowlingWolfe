package com.HowlingWolfe.HowlingWolfe.services;

import com.HowlingWolfe.HowlingWolfe.repositories.BoatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoatService {

    BoatRepo boatRepo;

    @Autowired
    BoatService(BoatRepo boatRepo){this.boatRepo = boatRepo;}

    @Transactional
    public String deleteBoat(String boatId){
        try{
            boatRepo.deleteByUUID(boatId);
            return "success";
        } catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }

    }

}
