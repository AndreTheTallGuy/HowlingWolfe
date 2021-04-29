package com.HowlingWolfe.HowlingWolfe.services;

import com.HowlingWolfe.HowlingWolfe.email.SendEmail;
import com.HowlingWolfe.HowlingWolfe.models.Customer;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public String sendEmail(String type, Customer customer){
        switch (type) {
            case "contact":
                SendEmail.send("contact", customer, null);
                SendEmail.send("contactJake", customer, null);
                break;
            case "lessons":
                SendEmail.send("lessons", customer, null);
                SendEmail.send("lessonsJake", customer, null);
                break;
            case "guided":
                SendEmail.send("guided", customer, null);
                SendEmail.send("guidedJake", customer, null);
                break;
        }
        return "Email Sent!";
    }
}
