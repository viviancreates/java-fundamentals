package com.airport.view;
import com.airport.repository.CSVReservationRepository;
import com.airport.domain.model.Flight;
import com.airport.domain.model.Passenger;
import com.airport.domain.model.Reservation;
import com.airport.domain.model.Aircraft;
import com.airport.domain.model.CommercialAircraft;
import com.airport.domain.model.PrivateJet;

import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * Hello world!
 *
 */
public class AirportTerminalApp {
    public static void main( String[] args ) {
        TerminalUtils io = new TerminalIO();
        boolean running = true;

        io.displayWelcome();

        /*
        System.out.println("1. Create Reservation");
        System.out.println("2. View All Reservations");
        System.out.println("3. Quit");
         */

        while(running) {
            io.displayMenu();
            int choice = io.getIntInput("Enter choice: ");

            switch (choice) {
                case 1:
                    displayCartCommand.execute();
                    break;

                case 2:
                    addItemCommand.execute();
                    break;

                case 3:
                    removeItemCommand.execute();
                    break;

                case 4:
                    checkoutCommand.execute();
                    break;

                case 5:
                    io.displayMessage("You are exiting the shopping cart. Thank you.");
                    running = false;

                case 6:
                    io.displayMessage("Try Again. Please select a choice from the Menu.");
                    break;
            }
        }
    }
}
