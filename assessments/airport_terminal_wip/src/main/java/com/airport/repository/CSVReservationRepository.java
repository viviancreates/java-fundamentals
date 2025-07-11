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

    //add addRes and getAllRes methods here -> talk to reservation service
    public void addReservation(Reservation reservation) {
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

        //load the reservation - try with resources
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String flightNumber = parts[0].trim();
                    LocalDate departureDate = LocalDate.parse(parts[1].trim());
                    BigDecimal ticketPrice = new BigDecimal(parts[2].trim());
                    String departureLocation = parts[3].trim();
                    String passengerName = parts[4].trim();
                    String passportNumber = parts[5].trim();
                    String aircraftModel = parts[6].trim();
                    String aircraftType = parts[7].trim();

                    //needd the aircraft in order to build the flight
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

                    Flight flight = new Flight(flightNumber, departureDate, ticketPrice, aircraft, departureLocation);
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
        //add a reservation and save - try with resources
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            //loop through each entry in the map
            //- key is flight number
            //-value is ArrayList<reservation>
            for (ArrayList<Reservation> reservationList : reservations.values()) {

                for (Reservation reservation : reservationList) {

                    //pull out flight and passenger objects from reservation
                    Flight flight = reservation.getFlight();
                    Passenger passenger = reservation.getPassenger();
                    Aircraft aircraft = flight.getAircraft();

                    //write to the csv
                    writer.printf("%s,%s,%.2f,%s,%s,%s,%s%n",
                            //flightnumber
                            flight.getFlightNumber(),
                            //departuredate
                            flight.getDepartureDate(),
                            //ticketprice
                            flight.getTicketPrice(),
                            //passengerName or None -> if the passenger is null or passenger is empty is true, write none, otherwise get name
                            (passenger == null || passenger.isEmpty()) ? "None" : passenger.getName(),
                            //passportNumber or None
                            (passenger == null || passenger.isEmpty()) ? "None" : passenger.getPassportNumber(),
                            //aircraftModel
                            aircraft.getModel(),
                            //aircraftTye
                            aircraft.getType()
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + filename, e);
        }

    }
}

