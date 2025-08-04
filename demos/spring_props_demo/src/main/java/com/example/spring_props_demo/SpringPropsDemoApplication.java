package com.example.spring_props_demo;

import com.example.spring_props_demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringPropsDemoApplication implements CommandLineRunner {
	@Autowired
	private EmailService primaryEmailService;

	@Autowired
	@Qualifier("gmailService")
	private EmailService gmailService;

	@Autowired
	@Qualifier("backupEmailService")
	private EmailService backupEmailService;

	@Value("${demo.testmessage}")
	private String testMessage;

	@Value("${demo.runallservices}")
	private boolean runAllServices;

	public static void main(String[] args) {
		SpringApplication.run(SpringPropsDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Email Service Demo Started...");

		System.out.println("Using primary service: ");
		primaryEmailService.send(testMessage);

		if (runAllServices) {
			System.out.println("Using Gmail Service (via Qualifier)");
			gmailService.send(testMessage);

			System.out.println("Using the backup service (via Bean)");
			backupEmailService.send(testMessage);
		}

		System.out.println("Demo complete.");
	}
}
