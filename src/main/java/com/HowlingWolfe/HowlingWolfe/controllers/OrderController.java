package com.HowlingWolfe.HowlingWolfe.controllers;

import com.HowlingWolfe.HowlingWolfe.models.Order;
import com.HowlingWolfe.HowlingWolfe.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/upcoming")
    public ResponseEntity<Set<Order>> getAllOrdersUpcoming(){
        System.out.println("received");
        Set<Order> response = orderService.getOrdersUpcoming();
        System.out.println("sending");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/date/{date}")
    public ResponseEntity<Set<Order>> getAllOrdersByDate(@PathVariable Date date){
        Set<Order> response = orderService.getOrdersByDate(date);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/today")
    public ResponseEntity<Set<Order>> getOrdersToday(){
        Set<Order> response = orderService.getTodaysOrders();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/post")
    public ResponseEntity<String> postOrder(@RequestBody Order order){
        System.out.println("post");
        System.out.println(order);
        String response = orderService.postOrder(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
