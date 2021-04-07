package com.HowlingWolfe.HowlingWolfe.controllers;

import com.HowlingWolfe.HowlingWolfe.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/post")
    public ResponseEntity<String> postOrder(@RequestBody String order){
        String response = orderService.postOrder(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
