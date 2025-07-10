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






/*
    //need to change this map -> otherwise will not save to reserfations csv -> use repo
    //1. REMOVE THIS MAPO
    //private Map<String, ArrayList<Passenger>> reservations = new HashMap<>();

    //ReservationService will use the data inside the CSVReservationRepository
    //so ther service tells it what to do
    //the repo is moving around the data?

    private final CSVReservationRepository repository;

    public ReservationService(CSVReservationRepository) {
        this.repository = repository;
        this.repository.init();
    }

    public void addPassengerToFlight(String flightNumber, Passenger passenger) {
        ArrayList<Passenger> passengersOnFlight = reservations.get(flightNumber);

        if (passengersOnFlight == null) {
            passengersOnFlight = new ArrayList<>();
            reservations.put(flightNumber, passengersOnFlight);
        }

        passengersOnFlight.add(passenger);
    }

    public ArrayList<Passenger> getPassengersOnFlight(String flightNumber) {
        ArrayList<Passenger> passengersOnFlight = reservations.get(flightNumber);

        if (passengersOnFlight == null) {
            return new ArrayList<>();
        }
        return passengersOnFlight;
    }
}

 */


    /*
    //Key: Flight number (String)
    //Value: List of passengers (ArrayList<Passenger>)
    private Map<String, ArrayList<Passenger>> reservations = new HashMap<>();
    //reservations is the hashmap
    //it is defined once the ReservationService object is created
    //Stays in memory as long as the app is running

    private final CSVReservationRepository repository;

    public ReservationService(CSVReservationRepository) {
        this.repository = repository;
        this.repository.init();
    }

    //method to add passender to flight
    // pass in available flights from repo and a passenger
    //flightNumber coming from repo
    //passenger coming from passenger model
    public void addPassengerToFlight(String flightNumber, Passenger passenger ) {
        //1. Check if flightNumber has a list initialized
        //passengersOnFlight is a variable to hold the list
        //.get(key) is a method from the Map
        //reservations.get pulls the list for that flight number
        // flight number is passed as a parameter to the method -> comes from flight object got from flight repository
        ArrayList<Passenger> passengersOnFlight= reservations.get(flightNumber);

        //2. If there is not a list already, create  new list
        if (passengersOnFlight == null) {
            passengersOnFlight = new ArrayList<>();
            reservations.put(flightNumber, passengersOnFlight);
        }

        //3. Add the passenger to the list
        passengersOnFlight.add(passenger);
    }



    //IT returns NULL Not EMPTY -> if a key does not exist in a hashmap, JAVA RETURNS NUYLL

    //method to retrieve all passengers booked on a specific flight
    public ArrayList<Passenger> getPassengersOnFlight(String flightNumber) {
        //1. Check if flightNumber has a list initialized
        //passengersOnFlight is a variable
        //reservations.get coming from flight model, repository
        ArrayList<Passenger> passengersOnFlight = reservations.get(flightNumber);


        //2. If there is not a list already, create  new list
        if (passengersOnFlight == null) {
            //return an empty array list
            return new ArrayList<>();
        }

        return passengersOnFlight;
    }

     */
