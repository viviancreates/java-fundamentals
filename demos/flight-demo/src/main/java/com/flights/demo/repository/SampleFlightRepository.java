package com.flights.demo.repository;

import org.springframework.stereotype.Repository;
import com.flights.demo.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SampleFlightRepository implements FlightRepository {

    @Override
    public List<Flight> loadFlights() {
        return List.of(
                new Flight("AA101", LocalDateTime.of(2025, 12, 15, 8, 30), "New York", "Boeing 737"),
                new Flight("DL205", LocalDateTime.of(2025, 12, 15, 10, 45), "Los Angeles", "Airbus A320"),
                new Flight("UA350", LocalDateTime.of(2025, 12, 15, 14, 20), "Chicago", "Boeing 777"),
                new Flight("SW112", LocalDateTime.of(2025, 12, 15, 16, 15), "Denver", "Boeing 737"),
                new Flight("JB401", LocalDateTime.of(2025, 12, 15, 18, 30), "Miami", "Airbus A321")
        );
    }
}
