package com.HowlingWolfe.HowlingWolfe.email;

import com.HowlingWolfe.HowlingWolfe.models.Boat;
import com.HowlingWolfe.HowlingWolfe.models.Customer;
import com.HowlingWolfe.HowlingWolfe.models.GiftCard;
import com.HowlingWolfe.HowlingWolfe.models.GiftObj;
import com.sun.mail.smtp.SMTPTransport;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class SendEmail {

    private static final String SMTP_SERVER = "smtp.office365.com";
    private static final String USERNAME = "jake@howlingwolfe.com";
    private static final String PASSWORD = System.getenv("EMAIL_PASSWORD");
    private static final String EMAIL_FROM = "jake@howlingwolfe.com";

    private static String emailTo = "";
    private static String emailSubject = "";
    private static StringBuilder emailText = new StringBuilder();

    public static void send(String type, Customer customer, List<Boat> boats) {

        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.TLSSocketFactory");
//        prop.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {

            messageResolver(type, customer, boats);

            msg.setFrom(new InternetAddress(EMAIL_FROM));

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo, false));
//            msg.setRecipients(Message.RecipientType.BCC,
//                    InternetAddress.parse("jake@gmail.com", false));

            msg.setSubject(emailSubject);

            // TEXT email
            //msg.setText(EMAIL_TEXT);

            // HTML email
            msg.setDataHandler(new DataHandler(new HTMLDataSource(emailText.toString())));


            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

            // connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);

            // send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public static void sendGiftCard(String type, GiftObj giftObj) {

        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.TLSSocketFactory");
//        prop.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {

            giftCardMessageResolver(type, giftObj);

            msg.setFrom(new InternetAddress(EMAIL_FROM));

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo, false));
//            msg.setRecipients(Message.RecipientType.BCC,
//                    InternetAddress.parse("jake@gmail.com", false));

            msg.setSubject(emailSubject);

            // TEXT email
            //msg.setText(EMAIL_TEXT);

            // HTML email
            msg.setDataHandler(new DataHandler(new HTMLDataSource(emailText.toString())));


            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

            // connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);

            // send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    static class HTMLDataSource implements DataSource {

        private String html;

        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            if (html == null) throw new IOException("html message is null!");
            return new ByteArrayInputStream(html.getBytes());
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }

        @Override
        public String getContentType() {
            return "text/html";
        }

        @Override
        public String getName() {
            return "HTMLDataSource";
        }
    }

    private static void messageResolver(String type, Customer customer, List<Boat> boats){
        switch(type){
            case "order":
                emailTo = customer.getEmail();
                emailSubject = "Thank you for your Reservation";
                emailText =

                        new StringBuilder("<img src='https://www.howlingwolfe.com/assets/HowlingWolfeColored.png' " +
                                "alt='Howling Wolfe Logo' width='300px'><br><br>");
                        emailText.append("<h1>Thank you ").append(customer.getFirstName()).append(" ")
                                .append(customer.getLastName()).append(" for your reservation of: </h1> <h3> <br> " +
                                "<br>");
                        for(Boat boat : boats){
                            emailText.append("Boat: ").append(boat.getBoat()).append("<br>");
                            emailText.append("Shuttle: ").append(boat.getShuttle()).append("<br>");
                            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
                            emailText.append("Date: ").append(dateFormat.format(boat.getDate())).append("<br>");
                            emailText.append("Time: ").append(boat.getTime()).append("<br>");
                            emailText.append("Duration: ").append(boat.getDuration()).append(" hours <br>");
                            emailText.append("Height: ").append(boat.getHeight()).append("<br>");
                            emailText.append("Weight: ").append(boat.getWeight()).append("<br> <br>");
                        }
                        emailText.append("Please meet at the <a href=\"https://www.google" +
                                ".com/maps/place/Aurora+Athletic+Club/@41.7855914,-88.3208956,15" +
                                ".08z/data=!4m5!3m4!1s0x880efad7812f555b:0x8d5c3884ae94eb7a!8m2!3d41.7860631!4d-88" +
                                ".3140031\" target=\"_blank\">Aurora Athletic Club </a> at least 15 minutes prior to " +
                                "your " +
                                "reservation time. <br><br>");
                        emailText.append("Please bring water to drink, a snack, dress in layers and be prepared to " +
                                "get wet, water shoes are recommended <br><br>" );
                        emailText.append(" We look forward to paddling with you soon! <br> " +
                        " HowlingWolfe Canoe & Kayak </h3>");
                break;
            case "orderJake":
                emailTo = "jake@howlingwolfe.com";
                emailSubject = "New Rental";
                emailText = new StringBuilder("<h1>" + customer.getFirstName() + " " + customer.getLastName() +
                        " has placed an order <br> </h1>" +
                        " Contact info: <br>Email: " + customer.getEmail() + "<br>Phone: " +
                        customer.getPhone() + " <br> <br>");
                        for(Boat boat : boats){
                            emailText.append("Boat: ").append(boat.getBoat()).append("<br>");
                            emailText.append("Shuttle: ").append(boat.getShuttle()).append("<br>");
                            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
                            emailText.append("Date: ").append(dateFormat.format(boat.getDate())).append("<br>");
                            emailText.append("Time: ").append(boat.getTime()).append("<br>");
                            emailText.append("Duration: ").append(boat.getDuration()).append("<br>");
                            emailText.append("Height: ").append(boat.getHeight()).append("<br>");
                            emailText.append("Weight: ").append(boat.getWeight()).append("<br> <br>");
                        }
                break;
            case "contact":
                emailTo = customer.getEmail();
                emailSubject = "Thank you for contacting us";
                emailText =
                        new StringBuilder("<h1>Thank you " + customer.getFirstName() + " " + customer.getLastName() +
                        " for contacting us. Someone will respond to your message as soon as " +
                        "possible<br> <br>" +
                        " HowlingWolfe Canoe & Kayak </h1>");
                break;
            case "contactJake":
                emailTo = "jake@howlingwolfe.com";
                emailSubject = "Someone has reached out";
                emailText =
                        new StringBuilder("<h1>" + customer.getFirstName() + " " + customer.getLastName() +
                                " has reached out using the contact form. </h1> <br> <br> " +
                                customer.getMessage() +
                                " <br> <br> Contact info: <br>Email: " + customer.getEmail() + "<br>Phone: " +
                                customer.getPhone() + " <br> <br>");
                break;
            case "lessons":
                emailTo = customer.getEmail();
                emailSubject = "Thank you for contacting us";
                emailText = new StringBuilder("<h1>Thank you " + customer.getFirstName() + " " + customer.getLastName() +
                        " for contacting us about lessons. Someone will respond to your message " +
                        "as soon as possible <br> <br> " +
                        " HowlingWolfe Canoe & Kayak </h1>");
                break;
            case "lessonsJake":
                emailTo = "jake@howlingwolfe.com";
                emailSubject = "Someone has reached out about lessons";
                emailText =
                        new StringBuilder("<h1>" + customer.getFirstName() + " " + customer.getLastName() +
                                " has reached out about lessons. </h1> <br> <br> " +
                                customer.getMessage() +
                                " <br> <br> Contact info: <br>Email: " + customer.getEmail() + "<br>Phone: " +
                                customer.getPhone() + " <br> <br>");
                break;
            case "guided":
                emailTo = customer.getEmail();
                emailSubject = "Thank you for contacting us";
                emailText = new StringBuilder("<h1>Thank you " + customer.getFirstName() + " " + customer.getLastName() +
                        " for contacting us about a guided trip. Someone will respond to your " +
                        "message as soon as possible <br> <br> " +
                        " HowlingWolfe Canoe & Kayak </h1>");
                break;
            case "guidedJake":
                emailTo = "jake@howlingwolfe.com";
                emailSubject = "Someone has reached out about a guided trip";
                emailText =
                        new StringBuilder("<h1>" + customer.getFirstName() + " " + customer.getLastName() +
                                " has reached out about a guided trip. </h1> <br> <br> " +
                                customer.getMessage() +
                                " <br> <br> Contact info: <br>Email: " + customer.getEmail() + "<br>Phone: " +
                                customer.getPhone() + " <br> <br>");
                break;
        }



    }

    private static void giftCardMessageResolver(String type, GiftObj giftObj){

        String fromName = giftObj.getFromName();
        String fromEmail = giftObj.getFromEmail();
        GiftCard giftCard = giftObj.getGiftCard();
        String recipient = giftCard.getEmail();

        switch (type){
            case "recipient":
                emailTo = recipient;
                emailSubject = "You have been given a gift card from " + fromName;
                emailText = new StringBuilder("<img src='https://www.howlingwolfe.com/assets/HowlingWolfeColored.png'" +
                        " alt='Howling Wolfe Logo' width='300px'><br><br>")
                        .append("<h1>Hello from HowlingWolfe Canoe and Kayak</h1><br><br>")
                        .append("<h3>")
                        .append(fromName).append(" has sent you a gift card in the amount of $")
                        .append(giftCard.getBalance()).append("<br><br>");

                        if(giftObj.getMessage().length() > 0){
                            emailText.append(fromName).append(" says:<br>")
                                    .append(giftObj.getMessage()).append("<br><br>");
                        }

                        emailText.append("To redeem your gift card, please visit <a href='https://www.howlingwolfe" +
                        ".com/rentals'>howlingwolfe.com</a> and make a reservation. <br><br>")
                        .append(" We look forward to paddling with you soon! <br> " +
                    " <a href='https://www.howlingwolfe.com'>HowlingWolfe Canoe & Kayak</a> </h3>");
                break;
            case "sender":
                emailTo = fromEmail;
                emailSubject = "Thank you for purchasing a gift card from HowlingWolfe!";
                emailText = new StringBuilder("<img src='https://www.howlingwolfe.com/assets/HowlingWolfeColored.png'" +
                        " alt='Howling Wolfe Logo' width='300px'><br><br>")
                        .append("<h1>Hello ").append(fromName).append(",</h1><br><br>")
                        .append("<h3> Thank you for purchasing a gift card!<br><br>")
                        .append("We have sent an email with the gift card and how to redeem it to: ")
                        .append(recipient).append("<br><br>")
                        .append("Thank you again, <br><br>")
                        .append("<a href='https://www.howlingwolfe.com'>HowlingWolfe Canoe & Kayak</a> </h3>");
                break;
            case "Jake":
                emailTo = "jake@howlingwolfe.com";
                emailSubject = "New Gift Card Purchased";
                emailText = new StringBuilder("<h1> New gift card was purchased</h1><br><br>")
                        .append("Recipient: ").append(recipient).append("<br>")
                        .append("Amount: $").append(giftCard.getBalance()).append("<br>")
                        .append("Card Number: ").append(giftCard.getCardNumber()).append("<br>")
                        .append("Sender: ").append(fromName).append("<br>")
                        .append("Sender's email: ").append(fromEmail).append("<br><br>")
                        .append("An email has been sent to both parties.");

        }
    }

}