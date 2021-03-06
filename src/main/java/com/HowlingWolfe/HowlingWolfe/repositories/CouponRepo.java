package com.HowlingWolfe.HowlingWolfe.repositories;

import com.HowlingWolfe.HowlingWolfe.models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    Coupon findByCode(String code);
}
