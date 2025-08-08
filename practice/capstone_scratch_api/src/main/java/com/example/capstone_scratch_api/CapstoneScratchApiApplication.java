package com.example.capstone_scratch_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CapstoneScratchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneScratchApiApplication.class, args);
		System.out.println("\n🚀 Crypto API Test Server Started!");
		System.out.println("📖 Test the 3 core actions:");
		System.out.println("   1. POST /api/test/create-wallet");
		System.out.println("   2. POST /api/test/fund-wallet");
		System.out.println("   3. POST /api/test/send-transaction");
		System.out.println("💡 Check wallet: GET /api/test/wallet/{walletId}");
		System.out.println("🌐 Server running on: http://localhost:8080\n");
	}


}
