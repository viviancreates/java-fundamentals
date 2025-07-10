package com.airport.domain.command;

public class GetAllReservationsCommand implements Command {
    private final ReservationService reservationService;
    private final TerminalIO io;

    public GetAllReservationsCommand(ReservationService reservationService, TerminalIO io) {
        this.reservationService = reservationService;
        this.io = io;
    }

    @Override
    public void execute() {
        ArrayList<Reservation> allReservations = reservationService.getAllReservations();

        if (allReservations.isEmpty()) {
            io.displayMessage("No reservations found");
            return;
        }

        io.displayMessage("===== ALL RESERVATIONS =====");

        //;loopand print :D
        for (Reservation reservation : allReservations) {
            io.displayMessage(
                    "Flight: " + reservation.getFlight().getFlightNumber() +
                            ", Passenger: " + reservation.getPassenger().getName() +
                            ", Passport: " + reservation.getPassenger().getPassportNumber() +
                            ", Departure Date: " + reservation.getFlight().getDepartureDate() +
                            ", Aircraft: " + reservation.getFlight().getAircraft().getModel() +
                            " ( " + reservation.getFlight().getAircraft().getType() + " )"
            );
        }
    }
}
