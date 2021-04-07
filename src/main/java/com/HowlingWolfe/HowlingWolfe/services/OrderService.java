package com.HowlingWolfe.HowlingWolfe.services;

import com.HowlingWolfe.HowlingWolfe.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {

    private OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public String postOrder(String order){
        System.out.println(order);
        return "Testing";
    }
}
