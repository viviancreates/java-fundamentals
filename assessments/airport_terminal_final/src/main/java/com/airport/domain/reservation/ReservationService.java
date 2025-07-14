package com.airport.domain.reservation;

import com.airport.domain.model.Passenger;
import com.airport.repository.CSVReservationRepository;
import com.airport.domain.model.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private final CSVReservationRepository repository;

    public ReservationService(CSVReservationRepository repository) {
        this.repository = repository;
        this.repository.init();
    }

    public void addReservation(Reservation reservation) {
        repository.addReservation(reservation);
    }

    public ArrayList<Reservation> getAllReservations() {
        return repository.getAllReservations();
    }
}
