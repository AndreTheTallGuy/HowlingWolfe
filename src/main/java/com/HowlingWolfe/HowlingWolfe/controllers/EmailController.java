package com.HowlingWolfe.HowlingWolfe.controllers;

import com.HowlingWolfe.HowlingWolfe.models.Customer;
import com.HowlingWolfe.HowlingWolfe.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/email")
public class EmailController {

    EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(path = "/email")
    public ResponseEntity<String> sendEmail(@RequestParam String type, @RequestBody(required = false) Customer customer){
        String response = emailService.sendEmail(type, customer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
