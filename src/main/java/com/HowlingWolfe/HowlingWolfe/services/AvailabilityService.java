package com.HowlingWolfe.HowlingWolfe.services;

import com.HowlingWolfe.HowlingWolfe.models.Availability;
import com.HowlingWolfe.HowlingWolfe.repositories.AvailabilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityService {

    private AvailabilityRepo availabilityRepo;

    @Autowired
    public AvailabilityService(AvailabilityRepo availabilityRepo) {this.availabilityRepo = availabilityRepo; }

    public Availability getBySubType(String subType){
        return availabilityRepo.findBySubType(subType);
    }

    public String update(Availability availObj) {
        if(availabilityRepo.findBySubType(availObj.getSubType()) == null){
           return postTripAvail(availObj);
        } else {
            try {
                Availability tripAvail = availabilityRepo.findBySubType(availObj.getSubType());
//                tripAvail.setId(availObj.getId());
//                tripAvail.setTripType(availObj.getTripType());
//                tripAvail.setSubType(availObj.getSubType());
                tripAvail.setDates(availObj.getDates());

                availabilityRepo.save(tripAvail);
                return "Updated!";
            } catch (Exception e){
                e.printStackTrace();
                return e.getMessage();
            }
        }
    }

    public String postTripAvail(Availability availObj) {
        try {
            availabilityRepo.save(availObj);
            return "Success!";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }
}
