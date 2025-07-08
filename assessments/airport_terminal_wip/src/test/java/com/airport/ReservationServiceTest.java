package com.airport;

import com.airport.domain.model.Passenger;
import com.airport.domain.reservation.ReservationService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationServiceTest {
    private ReservationService rs;

    @BeforeEach
    void setUp() {
        rs = new ReservationService();
    }

    @Test
    @DisplayName("ReservationService Initializes as Empty")
    public void reservationServiceInitializedEmpty() {
        ArrayList<Passenger> passengers = rs.getPassengersOnFlight("AA100");

        assertTrue(passengers.isEmpty());
    }

    @Test
    @DisplayName("Add a passenger to the flight")
    public void addPassengerToFlightAndCheckName() {
        Passenger passenger = new Passenger("Vivs", "11123");
        rs.addPassengerToFlight("AA100", passenger);
        ArrayList<Passenger> passengers = rs.getPassengersOnFlight("AA100");

        int expectedSize = 1;
        int actualSize = passengers.size();
        assertEquals(expectedSize, actualSize);

        String expectedName = "Vivs";
        String actualName = passengers.get(0).getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    @DisplayName("Add many passengers to the flight")
    public void addMultiplePassengersToFlightAndCheckSize() {
        Passenger passenger = new Passenger("Vivs", "11123");
        Passenger passenger2 = new Passenger("Ali", "11343223");
        Passenger passenger3 = new Passenger("Kobe", "11423123");
        Passenger passenger4 = new Passenger("Warren", "11234123");
        rs.addPassengerToFlight("AA100", passenger);
        rs.addPassengerToFlight("AA100", passenger2);
        rs.addPassengerToFlight("AA102", passenger3);
        rs.addPassengerToFlight("AA100", passenger3);

        ArrayList<Passenger> passengers = rs.getPassengersOnFlight("AA100");

        int expectedSize = 3;
        int actualSize = passengers.size();
        assertEquals(expectedSize, actualSize);
    }



}
