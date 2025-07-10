package com.airport.view;
import com.airport.repository.CSVReservationRepository;
import com.airport.domain.model.Flight;
import com.airport.domain.model.Passenger;
import com.airport.domain.model.Reservation;
import com.airport.domain.model.Aircraft;
import com.airport.domain.model.CommercialAircraft;
import com.airport.domain.model.PrivateJet;

import com.airport.domain.command.Command;
import com.airport.domain.command.CreateReservationCommand;
import com.airport.domain.command.ViewAllReservationsCommand;

import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * Hello world!
 *
 */
public class AirportTerminalApp {
    public static void main( String[] args ) {
        TerminalIO io = new TerminalIO();
        CSVReservationRepository repo = new CSVReservationRepository();

        //initialize -> loads the reservations from the file
        repo.init();
        Command createReservationCommand = new CreateReservationCommand(repo, io);
        Command viewAllReservationsCommand = new ViewAllReservationsCommand(repo, io);

        boolean running = true;


        io.displayWelcome();

        /*
        System.out.println("1. Create Reservation");
        System.out.println("2. View All Reservations");
        System.out.println("3. Quit");
         */

        while(running) {
            io.displayMenu();
            int choice = io.displayMenuAndGetChoice();

            switch (choice) {
                case 1:
                    createReservationCommand.execute();
                    break;

                case 2:
                    viewAllReservationsCommand.execute();
                    break;

                case 3:
                    removeItemCommand.execute();
                    break;

                case 4:
                    io.displayMessage("You are exiting Airport Terminal. Thank you.");
                    running = false;

                case 5:
                    io.displayError("Try Again. Please select a choice from the Menu.");
                    break;
            }
        }
        io.displayGoodbye();
    }
}
