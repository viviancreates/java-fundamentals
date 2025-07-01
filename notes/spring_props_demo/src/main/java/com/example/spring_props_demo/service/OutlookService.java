package com.example.spring_props_demo.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// @Primary annotation tells spring that this implementation is preferred when multiple beans of the same type exists.
@Service
@Primary
public class OutlookService implements EmailService {
    @Value("${email.outlook.enabled:true}")
    private boolean enabled;

    @Value("${email.outlook.server:smtp.outlook.com}")
    private String server;

    @Value("${email.outlook.port:587}")
    private int port;

    @Value("${email.outlook.security:STARTTLS}")
    private String security;

    @Override
    public void send(String message) {
        if (!enabled) {
            System.out.println("Tried to send mail but it's disabled.");
            return;
        }

        System.out.printf("Outlook send: %s using security mode: %s%n", message, security);
    }
}
