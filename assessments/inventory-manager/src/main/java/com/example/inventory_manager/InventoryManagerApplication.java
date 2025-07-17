package com.example.inventory_manager;


import com.example.inventory_manager.view.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryManagerApplication implements CommandLineRunner {

	@Autowired
	private ProductView productView;

	@Value("${inventory_manager.mode:productView}")
	private String mode;


	public static void main(String[] args) {
		SpringApplication.run(InventoryManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (mode.equalsIgnoreCase("productView")) {
			productView.run();
		} else {
			System.out.println("Add kiosk later");
		}
	}

}
