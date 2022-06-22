package com.HowlingWolfe.HowlingWolfe.controllers;

import com.HowlingWolfe.HowlingWolfe.models.Order;
import com.HowlingWolfe.HowlingWolfe.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
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
        Set<Order> response = orderService.getOrdersUpcoming();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/date/{date}")
    public ResponseEntity<Set<Order>> getAllOrdersByDate(@PathVariable String date) {
        System.out.println(date);
        TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(date);
        Instant i = Instant.from(ta);
        Date d = Date.from(i);
        System.out.println(d);
        Set<Order> response = orderService.getOrdersByDate(d);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/today")
    public ResponseEntity<Set<Order>> getOrdersToday(){
        Set<Order> response = orderService.getTodaysOrders();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/maxId")
    public ResponseEntity<Integer> getMaxOrderId(){
        Integer response = orderService.getMaxOrderId();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/post")
    public ResponseEntity<String> postOrder(@RequestBody Order order){
        System.out.println("in service");
        System.out.println(order);
        String response = orderService.postOrder(order);
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
