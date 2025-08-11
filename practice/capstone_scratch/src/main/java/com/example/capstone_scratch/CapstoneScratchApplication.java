package com.example.capstone_scratch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CapstoneScratchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneScratchApplication.class, args);

		System.out.println("\nAPI Server Started");
		System.out.println("Test endpoints: will add");
		System.out.println("Server: http://localhost:8080");

	}

}
