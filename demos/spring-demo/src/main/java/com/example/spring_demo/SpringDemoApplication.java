package com.example.spring_demo;

import com.example.spring_demo.service.NotificationService;
import com.example.spring_demo.service.XmlNotificationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringDemoApplication implements CommandLineRunner {
	private final NotificationService notificationService;

	public SpringDemoApplication(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
//		System.out.println("Annotation Dependency Injection");
//		notificationService.send("Hello from the annotation autowire injection!");

		System.out.println("XML Dependency Injection");
		ApplicationContext xmlContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		XmlNotificationService xmlService = xmlContext.getBean("xmlNotificationService", XmlNotificationService.class);
		xmlService.sendNotification("Hello from XML injection");

		((ClassPathXmlApplicationContext)xmlContext).close();
	}

}
