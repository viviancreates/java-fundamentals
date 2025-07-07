package com.flights.demo.repository;

import com.flights.demo.model.Flight;

import java.util.List;

public interface FlightRepository {
    List<Flight> loadFlights();
}
