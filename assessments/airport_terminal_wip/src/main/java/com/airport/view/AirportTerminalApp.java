package com.airport.view;
import com.airport.repository.CSVReservationRepository;

import com.airport.domain.command.Command;
import com.airport.domain.command.AddReservationCommand;
import com.airport.domain.command.GetAllReservationsCommand;
import com.airport.domain.reservation.ReservationService;


public class AirportTerminalApp {
    public static void main( String[] args ) {
        TerminalIO io = new TerminalIO();
        CSVReservationRepository repo = new CSVReservationRepository();
        ReservationService reservationService = new ReservationService(repo);

        //initialize -> loads the reservations from the file
        repo.init();
        //Command addReservationCommand = new AddReservationCommand(repo, io);
        //Command getAllReservationsCommand = new GetAllReservationsCommand(repo, io);
        Command addReservationCommand = new AddReservationCommand(reservationService, io);
        Command getAllReservationsCommand = new GetAllReservationsCommand(reservationService, io);

        boolean running = true;


        io.displayWelcome();

        /*
        System.out.println("1. Create Reservation");
        System.out.println("2. View All Reservations");
        System.out.println("3. Quit");
         */

        while(running) {
            int choice = io.displayMenuAndGetChoice();

            switch (choice) {
                case 1:
                    addReservationCommand.execute();
                    break;

                case 2:
                    getAllReservationsCommand.execute();
                    break;

                case 3:
                    io.displayMessage("You are exiting Airport Terminal. Thank you.");
                    running = false;
                    break;

                case 4:
                    io.displayError("Try Again. Please select a choice from the Menu.");
                    break;
            }
        }
        io.displayGoodbye();
    }
}
