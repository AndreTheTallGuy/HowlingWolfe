package com.HowlingWolfe.HowlingWolfe.controllers;

import com.HowlingWolfe.HowlingWolfe.models.ChargeObj;
import com.HowlingWolfe.HowlingWolfe.payment.StripeClient;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/payment")
public class PaymentController {

    private StripeClient stripeClient;

    @Autowired
    PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @PostMapping("/charge")
    public ResponseEntity<String> chargeCard(@RequestBody ChargeObj chargeObj) {
        String token = chargeObj.getToken();
        Double amount = chargeObj.getPrice();
        try{
            this.stripeClient.chargeCreditCard(token, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
