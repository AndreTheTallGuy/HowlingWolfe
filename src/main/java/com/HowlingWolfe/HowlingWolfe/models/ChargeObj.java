package com.HowlingWolfe.HowlingWolfe.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargeObj {

    private String token;
    private double price;
    private int orderId;
}
