package com.HowlingWolfe.HowlingWolfe.email;

import com.HowlingWolfe.HowlingWolfe.models.Boat;
import com.HowlingWolfe.HowlingWolfe.models.Customer;
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

    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String USERNAME = "noreply.howlingwolfe@gmail.com";
    private static final String PASSWORD = System.getenv("EMAIL_PASSWORD");
    private static final String EMAIL_FROM = "noreply.howlingwolfe@gmail.com";

    private static String emailTo = "";
    private static String emailSubject = "";
    private static StringBuilder emailText = new StringBuilder();

    public static void send(String type, Customer customer, List<Boat> boats) {

        Properties prop = System.getProperties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

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
                        new StringBuilder("<h1>Thank you " + customer.getFirstName() + " " + customer.getLastName() +
                        " for your order of: </h1> <h3> <br> <br>");
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
                        emailText.append(" We look forward to paddling with you soon! <br> " +
                        " HowlingWolfe Canoe & Kayak </h3>");
                break;
            case "orderJake":
                emailTo = "noreply.howlingwolfe@gmail.com";
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
                        "possible<br> " +
                        " HowlingWolfe Canoe & Kayak </h1>");
                break;
            case "contactJake":
                emailTo = "noreply.howlingwolfe@gmail.com";
                emailSubject = "Someone has reached out";
                emailText =
                        new StringBuilder("<h1>" + customer.getFirstName() + " " + customer.getLastName() +
                                " has reached out using the contact form. </h1> <br> <br> " +
                                customer.getMessage());
                break;
            case "lessons":
                emailTo = customer.getEmail();
                emailSubject = "Thank you for contacting us";
                emailText = new StringBuilder("<h1>Thank you " + customer.getFirstName() + " " + customer.getLastName() +
                        " for contacting us about lessons. Someone will respond to your message " +
                        "as soon as possible <br> " +
                        " HowlingWolfe Canoe & Kayak </h1>");
                break;
            case "lessonsJake":
                emailTo = "noreply.howlingwolfe@gmail.com";
                emailSubject = "Someone has reached out about lessons";
                emailText =
                        new StringBuilder("<h1>" + customer.getFirstName() + " " + customer.getLastName() +
                                " has reached out about lessons. </h1> <br> <br> " +
                                customer.getMessage());
                break;
            case "guided":
                emailTo = customer.getEmail();
                emailSubject = "Thank you for contacting us";
                emailText = new StringBuilder("<h1>Thank you " + customer.getFirstName() + " " + customer.getLastName() +
                        " for contacting us about a guided trip. Someone will respond to your " +
                        "message as soon as possible <br> " +
                        " HowlingWolfe Canoe & Kayak </h1>");
                break;
            case "guidedJake":
                emailTo = "noreply.howlingwolfe@gmail.com";
                emailSubject = "Someone has reached out about a guided trip";
                emailText =
                        new StringBuilder("<h1>" + customer.getFirstName() + " " + customer.getLastName() +
                                " has reached out about a guided trip. </h1> <br> <br> " +
                                customer.getMessage());
                break;
        }



    }


}