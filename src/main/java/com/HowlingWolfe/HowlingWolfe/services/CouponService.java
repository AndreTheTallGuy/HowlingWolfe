package com.HowlingWolfe.HowlingWolfe.services;

import com.HowlingWolfe.HowlingWolfe.models.Coupon;
import com.HowlingWolfe.HowlingWolfe.repositories.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {

    CouponRepo couponRepo;

    @Autowired
    public CouponService (CouponRepo couponRepo) { this.couponRepo = couponRepo;}

    public Coupon getCouponByCode(String code){
        return couponRepo.findByCode(code);
    }

    public List<Coupon> findAll(){
        return couponRepo.findAll();
    }

    public String post(Coupon coupon){
        try {
        couponRepo.save(coupon);
        return "Success";
    } catch (Exception e){
            System.out.println(e.getMessage());
            return "Failure";
        }
    }

    public String deleteById(int id){
        try {
        couponRepo.deleteById(id);
        return "Success";
    } catch (Exception e){
            System.out.println(e.getMessage());
            return "Failure";
        }
    }
}
