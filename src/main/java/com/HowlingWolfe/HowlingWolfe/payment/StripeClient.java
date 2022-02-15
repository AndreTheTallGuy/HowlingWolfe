package com.HowlingWolfe.HowlingWolfe.payment;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StripeClient {

    @Autowired
    StripeClient() {
        Stripe.apiKey = System.getenv("STRIPE_KEY");
    }

    public Charge chargeCreditCard(String token, double amount, int orderId) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);

        Map<String, Integer> initialMetadata = new HashMap<>();
        initialMetadata.put("order_id", orderId);
        chargeParams.put("metadata", initialMetadata);

        return Charge.create(chargeParams);
    }
}