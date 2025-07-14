package com.airport.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import com.airport.domain.model.Passenger;
import com.airport.domain.model.Flight;
import com.airport.domain.model.Reservation;
import com.airport.domain.model.Aircraft;
import com.airport.domain.model.CommercialAircraft;
import com.airport.domain.model.PrivateJet;

public class CSVReservationRepository {
    private final Map<String, ArrayList<Reservation>> reservations = new HashMap<>();
    private String filename = "data/reservations.csv";

    public void init() {
        loadFromFile();
    }

    public void addReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation cannot be null");
        }

        String flightNumber = reservation.getFlight().getFlightNumber();
        ArrayList<Reservation> reservationList = reservations.get(flightNumber);

        if (reservationList == null) {
            reservationList = new ArrayList<>();
            reservations.put(flightNumber, reservationList);
        }

        reservationList.add(reservation);

        saveToFile();
    }

    public ArrayList<Reservation> getAllReservations() {
        ArrayList<Reservation> all = new ArrayList<>();
        for (ArrayList<Reservation> list : reservations.values()) {
            all.addAll(list);
        }
        return all;
    }

    private void loadFromFile() {
        File file = new File(filename);
        if (!file.exists()) {
            return;
        }

        reservations.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String flightNumber = parts[0].trim();
                    LocalDate departureDate = LocalDate.parse(parts[1].trim());
                    BigDecimal ticketPrice = new BigDecimal(parts[2].trim());
                    String passengerName = parts[3].trim();
                    String passportNumber = parts[4].trim();
                    String aircraftModel = parts[5].trim();
                    String aircraftType = parts[6].trim();

                    Aircraft aircraft = null;
                    if ("Commercial".equalsIgnoreCase(aircraftType)) {
                        int capacity = 1;
                        int fuelCapacity = 1;
                        String airline = " ";
                        aircraft = new CommercialAircraft(aircraftModel, capacity, fuelCapacity, airline);
                    } else if ("PrivateJet".equalsIgnoreCase(aircraftType)) {
                        int capacity = 1;
                        int fuelCapacity = 1;
                        boolean hasLuxuryService = true;
                        int maxSpeed = 1;
                        aircraft = new PrivateJet(aircraftModel, capacity, fuelCapacity, hasLuxuryService, maxSpeed);
                    } else {
                        throw new RuntimeException("Invalid");
                    }

                    Flight flight = new Flight(flightNumber, departureDate, ticketPrice, aircraft, "");
                    Passenger passenger = new Passenger(passengerName, passportNumber);
                    Reservation reservation = new Reservation(flight, passenger);

                    ArrayList<Reservation> reservationList = reservations.get(flightNumber);
                    if (reservationList == null) {
                        reservationList = new ArrayList<>();
                        reservations.put(flightNumber, reservationList);
                    }
                    reservationList.add(reservation);
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error parsing number from file: " + filename, e);
        }
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (ArrayList<Reservation> list : reservations.values()) {
                for (Reservation reservation : list) {
                    Flight flight = reservation.getFlight();
                    Passenger passenger = reservation.getPassenger();
                    Aircraft aircraft = flight.getAircraft();

                    writer.printf("%s,%s,%.2f,%s,%s,%s,%s%n",
                            flight.getFlightNumber(),
                            flight.getDepartureDate(),
                            flight.getTicketPrice(),
                            (passenger == null || passenger.isEmpty()) ? "None" : passenger.getName(),
                            (passenger == null || passenger.isEmpty()) ? "None" : passenger.getPassportNumber(),
                            aircraft.getModel(),
                            aircraft.getType()
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + filename, e);
        }
    }
}

