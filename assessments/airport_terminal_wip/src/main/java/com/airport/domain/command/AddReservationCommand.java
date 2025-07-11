package com.airport.domain.command;

import com.airport.domain.reservation.ReservationService;
import com.airport.view.TerminalIO;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.airport.domain.model.Reservation;
import com.airport.domain.model.Aircraft;
import com.airport.domain.model.CommercialAircraft;
import com.airport.domain.model.PrivateJet;
import com.airport.domain.model.Flight;
import com.airport.domain.model.Passenger;

public class AddReservationCommand implements Command {

    private final ReservationService reservationService;
    private final TerminalIO io;

    public AddReservationCommand(ReservationService reservationService, TerminalIO io) {
        this.reservationService = reservationService;
        this.io = io;
    }

    @Override
    public void execute() {
        io.displayMessage("\n=== Create New Reservation ===");

        //input
        String flightNumber = io.getStringInput("Enter Flight Number: ");
        String dateInput = io.getStringInput("Enter Departure Date (YYYY-MM-DD): ");
        LocalDate departureDate = LocalDate.parse(dateInput);
        BigDecimal ticketPrice = io.getBigDecimalInput("Enter Ticket Price: ");
        String departureLocation = io.getStringInput("Enter Departure Location: ");
        String passengerName = io.getStringInput("Enter Passenger Name: ");
        String passportNumber = io.getStringInput("Enter Passport Number: ");
        String aircraftType = io.getStringInput("Enter Aircraft Type (Commercial or PrivateJet): ");
        String aircraftModel = io.getStringInput("Enter Aircraft Model: ");

        Aircraft aircraft = null;

        if (aircraftType.equalsIgnoreCase("Commercial")) {
            int capacity = 1;
            int fuelCapacity = 1;
            String airline = "Delta";

            aircraft = new CommercialAircraft(aircraftModel, capacity, fuelCapacity, airline);
        } else if (aircraftType.equalsIgnoreCase("PrivateJet")) {
            int capacity = 1;
            int fuelCapacity = 1;
            boolean hasLuxuryService = true;
            int maxSpeed = 1;

            aircraft = new PrivateJet(aircraftModel, capacity, fuelCapacity, hasLuxuryService, maxSpeed);
        } else {
            io.displayError("Invalid aircraft type entered.");
            return;
        }

        Flight flight = new Flight(flightNumber, departureDate, ticketPrice, aircraft, departureLocation);
        Passenger passenger = new Passenger(passengerName, passportNumber);
        Reservation reservation = new Reservation(flight, passenger);


        reservationService.addReservation(reservation);
        io.displayMessage("Reservation created and saved!");
    }
}
