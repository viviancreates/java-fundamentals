package com.flights.demo.repository;

import com.flights.demo.model.Flight;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

@Repository
public class CSVFlightRepository implements FlightRepository {
    private static final String CSV_FILE_PATH = "data/flights.csv";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public List<Flight> loadFlights() {
        List<Flight> flights = new ArrayList<>();
        File csvFile = new File(CSV_FILE_PATH);

        if (!csvFile.exists()) {
            return flights;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                // Skip header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 4) {
                    try {
                        Flight flight = new Flight(
                                parts[0].trim(),
                                LocalDateTime.parse(parts[1].trim(), DATE_TIME_FORMATTER),
                                parts[2].trim(),
                                parts[3].trim()
                        );
                        flights.add(flight);
                    } catch (Exception e) {
                        System.err.println("Error parsing line: " + line + " - " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return flights;
    }
}
