package com.rulex.rulex.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public  EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void SendEmail(String to , String Subject, String body){
        SimpleMailMessage Message = new SimpleMailMessage();
        Message.setTo(to);
        Message.setSubject(Subject);
        Message.setText(body);
        javaMailSender.send(Message);
    }
}
