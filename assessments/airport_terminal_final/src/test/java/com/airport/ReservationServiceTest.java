package com.airport;

import com.airport.domain.model.Passenger;
import com.airport.domain.model.Flight;
import com.airport.domain.model.Reservation;
import com.airport.domain.model.CommercialAircraft;
import com.airport.domain.reservation.ReservationService;
import com.airport.repository.CSVReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationServiceTest {
    private ReservationService rs;

    @BeforeEach
    void setUp() {
        //wipe the CSV file
        File file = new File("data/reservations.csv");
        if (file.exists()) {
            file.delete();
        }

        //create a new empty file
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Could not reset test CSV file", e);
        }

        CSVReservationRepository repository= new CSVReservationRepository();
        rs = new ReservationService(repository);
    }

    @Test
    @DisplayName("ReservationService Initializes as Empty")
    public void reservationServiceInitializedEmpty() {
        List<Reservation> reservations = rs.getAllReservations();
        assertTrue(reservations.isEmpty());
    }

    @Test
    @DisplayName("Add a passenger to the flight")
    public void addPassengerToFlightAndCheckName() {
        Passenger passenger = new Passenger("Vivs", "11123");

        Flight flight = new Flight(
                "AA100",
                LocalDate.of(2025, 12, 15),
                new BigDecimal("200.00"),
                new CommercialAircraft("Boeing 737", 200, 1000, "American"),
                "New York"
        );

        Reservation reservation = new Reservation(flight, passenger);
        rs.addReservation(reservation);

        List<Reservation> reservations = rs.getAllReservations();
        assertEquals(1, reservations.size());
        assertEquals("Vivs", reservations.get(0).getPassenger().getName());
    }

    @Test
    @DisplayName("Add many passengers to the flight")
    public void addMultiplePassengersToFlightAndCheckSize() {
        Passenger passenger1 = new Passenger("Vivs", "11123");
        Passenger passenger2 = new Passenger("Ali", "11343223");
        Passenger passenger3 = new Passenger("Kobe", "11423123");

        Flight flight1 = new Flight("AA100",
                LocalDate.of(2025, 12, 15),
                new BigDecimal("200.00"),
                new CommercialAircraft("Boeing 737", 200, 1000, "American"),
                "New York");

        Flight flight2 = new Flight("AA102",
                LocalDate.of(2025, 12, 15),
                new BigDecimal("400.00"),
                new CommercialAircraft("Airbus A320", 300, 2000, "Delta"),
                "Los Angeles");

        rs.addReservation(new Reservation(flight1, passenger1));
        rs.addReservation(new Reservation(flight1, passenger2));
        rs.addReservation(new Reservation(flight1, passenger3));
        rs.addReservation(new Reservation(flight2, passenger3));

        List<Reservation> reservations = rs.getAllReservations();
        assertEquals(4, reservations.size());
    }

}
