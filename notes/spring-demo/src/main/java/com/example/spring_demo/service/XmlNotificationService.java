package com.example.spring_demo.service;

public class XmlNotificationService {
    private final EmailService emailService;
    private final String replyTo;

    // Constructor for XML dependency injection
    public XmlNotificationService(EmailService emailService, String replyTo) {
        this.emailService = emailService;
        this.replyTo = replyTo;
    }

    public void sendNotification(String message) {
        System.out.println("XmlNotificationService: Processing notification... from " + replyTo);
        emailService.sendEmail(message);
    }
}
