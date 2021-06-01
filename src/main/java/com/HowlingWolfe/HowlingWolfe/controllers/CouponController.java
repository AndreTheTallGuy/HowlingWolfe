package com.HowlingWolfe.HowlingWolfe.controllers;

import com.HowlingWolfe.HowlingWolfe.models.Coupon;
import com.HowlingWolfe.HowlingWolfe.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "coupon")
public class CouponController {

    CouponService couponService;

    @Autowired
    public CouponController (CouponService couponService) {this.couponService = couponService;}

    @GetMapping(path = "{code}")
    public ResponseEntity<Coupon> getCouponByCode(@PathVariable String code){
        Coupon response = this.couponService.getCouponByCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Coupon>> getAllCoupons(){
        List<Coupon> response = couponService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/post")
    public ResponseEntity<String> postCoupon(@RequestBody Coupon coupon){
        System.out.println(coupon);
        String response = couponService.post(coupon);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        String response = couponService.deleteById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
