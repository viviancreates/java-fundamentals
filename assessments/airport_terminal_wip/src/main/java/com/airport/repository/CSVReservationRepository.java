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
import java.math.LocalDate;
import java.util.*;


import com.airport.domain.model.Passenger;
import com.airport.domain.model.Flight;
import com.airport.domain.model.Reservation;
import com.airport.domain.model.Aircraft;
import com.airport.domain.model.CommercialAircraft;
import com.airport.domain.model.PrivateJet;



public class CSVReservationRepository {

    private final Map<String, ArrayList<Passenger>> reservations = new HashMap<>();
    private String filename = "data/reservations.csv";

    private void loadFromFile() {
        File file = new File(filename);
        if (!file.exists()) {
            return; // No file exists yet, start with empty inventory
        }

        //load the reservation - try with resources
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            //
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
            }


//flightNumber,departureDate,ticketPrice,passengerName,passportNumber,aircraftModel,aircraftType
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String flightNumber = parts[0].trim();
                    LocalDate departuredate = LocalDate.parseInt(parts[1].trim());
                    BigDecimal ticketPrice = new BigDecimal(parts[2].trim());
                    String passengerName = parts[3].trim();
                    String passportNumnber = parts[4].trim();
                    String AircraftModel = parts[5].trim();
                    String aircraftType = parts[6].trim();
                    String de = parts[6].trim();

                    //Build aircraft like build book
                    Flight flight = new Flight(flightNumber,departureDate,ticketPrice, aircraft, departureLocation)
            /*
             while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String isbn = parts[0].trim();
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    String genre = parts[3].trim();
                    int quantity = Integer.parseInt(parts[4].trim());
                    BigDecimal price = new BigDecimal(parts[5].trim());

                    Book book = new Book(isbn, title, author, genre);
                    InventoryItem item = new InventoryItem(book, quantity, price);
                    inventory.put(isbn, item);
                }
            }
             */
        } catch (IOException e) {
            throw new RuntimeException("Error parsing number from file: " + filename, e);
        }
    }


     private void saveToFile() {
         //add a reservation and save - try with resources
         try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
             /*
             for (InventoryItem item : inventory.values()) {
                 Book book = item.getBook();
                 writer.printf("%s,%s,%s,%s,%d,%.2f%n",
                         book.isbn(),
                         book.title(),
                         book.author(),
                         book.genre(),
                         item.getQuantity(),
                         item.getPrice());
             }

              */
         } catch (IOException e) {
             throw new RuntimeException("Error writing to file: " + filename, e);
         }

     }


}

//flightNumber,departureDate,ticketPrice,passengerName,passportNumber,aircraftModel,aircraftType
//AA101,2024-05-10,299.99,Alice Smith,P12345,Boeing 737,Commercial