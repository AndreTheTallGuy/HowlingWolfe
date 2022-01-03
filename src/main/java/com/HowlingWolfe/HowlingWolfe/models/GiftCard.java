package com.HowlingWolfe.HowlingWolfe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "giftcards")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GiftCard {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "card_number")
    private int cardNumber;

    @Column(name = "balance")
    private int balance;

    @Column(name = "email")
    private String email;

    @Column(name = "purchased_on")
    private Date purchased_on = new Date();


}
