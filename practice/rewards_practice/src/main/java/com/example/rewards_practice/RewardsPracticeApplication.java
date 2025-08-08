package com.example.rewards_practice;

import com.example.rewards_practice.cdp.CdpClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RewardsPracticeApplication implements CommandLineRunner {

	private final CdpClient cdpClient;

	public RewardsPracticeApplication(CdpClient cdpClient) {
		this.cdpClient = cdpClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(RewardsPracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cdpClient.createEvmAccount();
	}
}
