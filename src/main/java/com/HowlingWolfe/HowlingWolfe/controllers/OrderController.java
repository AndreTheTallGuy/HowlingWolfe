package com.HowlingWolfe.HowlingWolfe.controllers;

import com.HowlingWolfe.HowlingWolfe.models.Order;
import com.HowlingWolfe.HowlingWolfe.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> response = orderService.getOrders();
//        for(Order order : response){
//            System.out.println(order);
//        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/post")
    public ResponseEntity<String> postOrder(@RequestBody Order order){
        String response = orderService.postOrder(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
