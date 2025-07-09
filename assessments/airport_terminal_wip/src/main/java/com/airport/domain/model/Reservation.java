package com.airport.domain.model;

//in the same package so no need to import
//import com.airport.domain.model.Flight;
//import com.airport.domain.model.Passenger;
//This class represents one booking -> it represents one flight object and one passenger object on the flight

public class Reservation {
    //holds all details on flight (flight is its own class)
    private final Flight flight;
    //holds all details on passenger (passenger is its own class)
    private final Passenger passenger;

    //this will come from loadFromFile() method
    public Reservation(Flight flight, Passenger passenger) {
        this.flight = flight;
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    //no need for setters, edit others
}
