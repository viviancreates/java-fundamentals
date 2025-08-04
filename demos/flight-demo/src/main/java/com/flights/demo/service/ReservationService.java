package com.flights.demo.service;

import com.flights.demo.model.Flight;
import com.flights.demo.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    // hold the injected repository
    private final FlightRepository flightRepository;

    @Autowired
    public ReservationService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAvailableFlights() {
        return flightRepository.loadFlights();
    }
}
