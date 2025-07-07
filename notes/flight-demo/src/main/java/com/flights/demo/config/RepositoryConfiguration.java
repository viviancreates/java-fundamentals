package com.flights.demo.config;

import com.flights.demo.repository.CSVFlightRepository;
import com.flights.demo.repository.FlightRepository;
import com.flights.demo.repository.SampleFlightRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class that creates FlightRepository beans based on application properties
 */
@Configuration
public class RepositoryConfiguration {
    // load from application.properties, with a default of sample if the value is missing
    @Value("${flight.repository.source:sample}")
    private String repositorySource;

    // create the appropriate repository implementation based on the source
    @Bean
    public FlightRepository flightRepository() {
        switch (repositorySource.toLowerCase()) {
            case "csv":
                System.out.println("Configuring CSV Flight Repository");
                return new CSVFlightRepository();
            case "sample":
            default:
                System.out.println("Configuring Sample Flight Repository");
                return new SampleFlightRepository();
        }
    }
}
