package com.airport.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Flight {
    private String flightNumber;
    private LocalDate departureDate;
    private BigDecimal ticketPrice;
    private Aircraft aircraft;
    private String departureLocation;

    public Flight(String flightNumber, LocalDate departureDate, BigDecimal ticketPrice, Aircraft aircraft, String departureLocation) {
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.ticketPrice = ticketPrice;
        this.aircraft = aircraft;
        this.departureLocation = departureLocation;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }
}
