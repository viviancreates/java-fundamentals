package com.airport.repository;

import com.airport.domain.model.Flight;
import java.util.List;

public interface FlightRepository {
    List<Flight> loadFlights();
}
