package com.airport.repository;

import com.airport.domain.model.Flight;
import com.airport.domain.model.CommercialAircraft;
import com.airport.domain.model.PrivateJet;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;

public class SampleFlightRepository implements FlightRepository {

    @Override
    public List<Flight> loadFlights() {
        return Arrays.asList(
                new Flight("AA101", LocalDate.of(2025, 12, 15),
                        new BigDecimal("200.00"),
                        new CommercialAircraft("Boeing 737", 200, 1000, "American"),
                        "New York"),

                new Flight("DL205", LocalDate.of(2025, 12, 15),
                        new BigDecimal("400.00"),
                        new CommercialAircraft("Airbus A320", 300, 2000, "Delta"),
                        "Los Angeles"),

                new Flight("UA350", LocalDate.of(2025, 12, 15),
                        new BigDecimal("300.00"),
                        new CommercialAircraft("Boeing 777", 300, 3000, "United"),
                        "Chicago"),

                new Flight("SW112", LocalDate.of(2025, 12, 15),
                        new BigDecimal("3000.00"),
                        new PrivateJet("PJName111", 5, 1000, true, 1000),
                        "Denver"),

                new Flight("JB401", LocalDate.of(2025, 12, 15),
                        new BigDecimal("2000.00"),
                        new PrivateJet("Pjpj11", 10, 2000, true, 1500),
                        "Miami")
        );
    }
}
//String model, int capacity, double fuelCapacity, boolean hasLuxuryService, int maxSpeed