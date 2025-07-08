package com.airport;

import com.airport.domain.model.Passenger;
import com.airport.domain.reservation.ReservationService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

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



}
