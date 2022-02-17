package com.HowlingWolfe.HowlingWolfe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "coupon")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Coupon {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "gooduntil")
    private Date goodUntil;

    @Column(name = "discount")
    private int discount;

    @Column(name = "discountType")
    private String discountType;

    @ElementCollection
    @Column(name = "whenGood")
    private List<Date> whenGood = new ArrayList<Date>();

}
