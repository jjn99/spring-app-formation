package com.example.MicroServiceFormation.Utils;

import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailUtils {
    
    @Autowired 
    private JavaMailSender mail;
    
    public void sendSimpleMessage(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sonabel.dfp@gmail.com");
        message.setSentDate(new Date());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mail.send(message);
    }
    
    public void forgotPasswordMessage(String to, String subject, String password) throws MessagingException{
        MimeMessage message =  mail.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom("sonabel.dfp@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        String profil ="<p> <b>Details du compte </b><br><b>Email: </b>"+to+"<br><b>Password: </b>"+password+"</p>";
        message.setContent(profil, "text/html");
        mail.send(message);
    }
    
}
