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

import java.util.*;

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

    public Set<Order> getOrdersByDate(){ return orderRepo.findByDate();    }

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
            return "Order has failed";
        }
    }
}
