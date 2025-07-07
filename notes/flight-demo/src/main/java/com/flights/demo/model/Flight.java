package com.flights.demo.model;

import java.time.LocalDateTime;

public record Flight(String flightNumber, LocalDateTime dateTime, String destination, String aircraftType) {
}
