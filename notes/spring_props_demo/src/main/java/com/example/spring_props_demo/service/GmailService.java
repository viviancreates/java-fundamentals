package com.example.spring_props_demo.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class GmailService implements EmailService {

    @Value("${email.gmail.enabled:true}")
    private boolean enabled;

    @Value("${email.gmail.server:smtp.gmail.com}")
    private String server;

    @Value("${email.gmail.port:587}")
    private int port;

    @Override
    public void send(String message) {
        if (!enabled) {
            System.out.println("Tried to send mail but it's disabled.");
            return;
        }

        System.out.printf("Gmail send: %s%n", message);
    }
}
