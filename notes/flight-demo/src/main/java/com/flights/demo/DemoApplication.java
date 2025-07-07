package com.flights.demo;

import com.flights.demo.model.Flight;
import com.flights.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private ReservationService reservationService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("=== Flight Reservation System Demo ===");
		System.out.println("Demonstrating Repository Injection Pattern");

		List<Flight> flights = reservationService.getAvailableFlights();
		displayFlights(flights);

		System.out.println("=== Demo Complete ===");
		System.out.println("Try changing 'flight.repository.source' in application.properties");
		System.out.println("Options: 'sample' or 'csv'");
	}

	private static void displayFlights(List<Flight> flights) {
		System.out.println("\n=== Available Flights ===");

		System.out.println("Total Flights: " + flights.size());
		System.out.println();

		for (Flight flight : flights) {
			System.out.printf("Flight: %s | Date: %s | Destination: %s | Aircraft: %s%n",
					flight.flightNumber(),
					flight.dateTime(),
					flight.destination(),
					flight.aircraftType()
			);
		}
		System.out.println();
	}
}
