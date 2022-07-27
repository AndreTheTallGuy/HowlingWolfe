package com.HowlingWolfe.HowlingWolfe.services;

import com.HowlingWolfe.HowlingWolfe.models.Availability;
import com.HowlingWolfe.HowlingWolfe.models.SameDayBool;
import com.HowlingWolfe.HowlingWolfe.repositories.AvailabilityRepo;
import com.HowlingWolfe.HowlingWolfe.repositories.SameDayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityService {

    private AvailabilityRepo availabilityRepo;
    private SameDayRepo sameDayRepo;

    @Autowired
    public AvailabilityService(AvailabilityRepo availabilityRepo, SameDayRepo sameDayRepo) {this.availabilityRepo =
            availabilityRepo; this.sameDayRepo = sameDayRepo;}

    public Availability getBySubType(String subType){
        return availabilityRepo.findBySubType(subType);
    }

    public String update(Availability availObj) {
        if(availabilityRepo.findBySubType(availObj.getSubType()) == null){
           return postTripAvail(availObj);
        } else {
            try {
                Availability tripAvail = availabilityRepo.findBySubType(availObj.getSubType());
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

    public List<SameDayBool> getSameDay() {
        return sameDayRepo.findAll();
    }

    public String toggleSameDay(SameDayBool sameDay) {
        SameDayBool bool = sameDayRepo.findByType(sameDay.getType());
        bool.setSameDay(sameDay.isSameDay());
        try {
            sameDayRepo.save(bool);
            return "Success!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
