package com.example.spring_demo.service;
import org.springframework.stereotype.Service;

@Service("gmailService")
public class GmailService implements EmailService {
    @Override
    public void sendEmail(String message) {
        System.out.println("Gmail: Sending email - " + message);
    }
}
