package com.uc.dei.is.Assignment2.business.manager;

import com.uc.dei.is.Assignment2.data.models.Item;
import sun.nio.cs.IBM737;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Email {

    @Resource(name="java:jboss/mail/Default")
    private Session session;

    public static void send(String recipient, List<Item> items) throws Exception{
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "assignment2.mybayis@gmail.com";
        String password = "1qazZAQ!";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recipient, items);

        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient, List<Item> items){

        String text = "Last three added items:\n\n";
        for(Item i : items){
            text += i.toString();
            text += '\n';
        }

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Database Report");
            message.setText(text);
            return message;
        } catch (Exception ex){
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }
}