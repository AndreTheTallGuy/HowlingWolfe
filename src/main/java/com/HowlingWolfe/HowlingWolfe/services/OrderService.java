package com.HowlingWolfe.HowlingWolfe.services;

import com.HowlingWolfe.HowlingWolfe.email.SendEmail;
import com.HowlingWolfe.HowlingWolfe.models.Boat;
import com.HowlingWolfe.HowlingWolfe.models.Customer;
import com.HowlingWolfe.HowlingWolfe.models.Order;
import com.HowlingWolfe.HowlingWolfe.repositories.BoatRepo;
import com.HowlingWolfe.HowlingWolfe.repositories.CustomerRepo;
import com.HowlingWolfe.HowlingWolfe.repositories.OrderRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepo orderRepo;
    private CustomerRepo customerRepo;
    private BoatRepo boatRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, CustomerRepo customerRepo,
                        BoatRepo boatRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.boatRepo = boatRepo;
    }

    public List<Order> getOrders(){ return orderRepo.findAll(); }

    public Set<Order> getOrdersByDate(Date date) {
        Set<Order> upcomingOrders = orderRepo.findUpcoming();
        Set<Order> result = new HashSet<Order>();
        System.out.println(upcomingOrders);
//        Set<Order> test = upcomingOrders.stream()
//                .flatMap(f -> f.getBoats()
//                        .stream()
//                        .filter(boat -> boat.getDate().equals(date))
//                        .map(o-> o.getOrder()));
//        for (int i = 0; i < upcomingOrders.size(); i++) {
//            for (int j = 0; j < upcomingOrders[i].getBoats().size(); j++) {
//
//            }
//        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


        for (Order order : upcomingOrders) {
            for (Boat boat : order.getBoats()) {
                String boatDate = simpleDateFormat.format(boat.getDate());
                String compareDate = simpleDateFormat.format(date);

                System.out.println("Boat date: " + boatDate + " - Date: " + compareDate);
                if(boatDate.equals(compareDate)){
                    System.out.println("matched!");
                    System.out.println(order);
                    result.add(order);
                }
            }
        }

        return result;
    }

    public Set<Order> getOrdersUpcoming(){ return orderRepo.findUpcoming();}

    public Set<Order> getTodaysOrders(){
//        DateFormat date = new
        String thing = "";
        return orderRepo.getTodaysOrders(thing);
    }

    public Integer getMaxOrderId(){ return orderRepo.getMaxOrderId();}


    public String postOrder(Order orderObj){
        SendEmail.send("order", orderObj.getCustomer(), orderObj.getBoats());
        SendEmail.send("orderJake", orderObj.getCustomer(), orderObj.getBoats());

        try {
            customerRepo.save(orderObj.getCustomer());
            Order order = orderRepo.save(orderObj);
            List<Boat> list = new ArrayList<>();
            for (Boat boat : orderObj.getBoats()) {
                boat.setOrder(order);
                list.add(boat);
            }
            for(Boat boat : list){
                boatRepo.save(boat);
            }

//
            return "Order Success!";

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
