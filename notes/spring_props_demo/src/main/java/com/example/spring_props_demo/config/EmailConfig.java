package com.example.spring_props_demo.config;

import com.example.spring_props_demo.service.EmailService;
import com.example.spring_props_demo.service.OutlookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Value("${email.backup.enabled:false}")
    private boolean backupEnabled;

    @Bean("backupEmailService")
    public EmailService backupEmailService() {
        // We could implement complex logic here to decide which service to create or configure

        if (!backupEnabled) {
            // null pattern
            return new EmailService() {
                @Override
                public void send(String message) {
                    System.out.println("Backup email service is disabled.");
                }
            };
        }

        return new EmailService() {
            @Override
            public void send(String message) {
                System.out.println("Backup Email: " + message);
            }
        };
    }
}
