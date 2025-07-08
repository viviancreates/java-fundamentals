# Airport Terminal Assessment
This is a Java built airport terminal program built to practice Java OOP, file handling, and unit testing

## Structure

The application uses:
- `Passenger`, `Flight`, `Aircraft` (abstract), `CommercialAircraft`, `PrivateJet` -> domain models
- `FlightRepository` (interface) -> load available flights
- `SampleFlightRepository` -> provides sample flights with commercial and private jets
- `HashMap<String, ArrayList<Passenger>>` -> store flight reservations:
    - Key - Flight number (String)
    - Value - List of passengers (`ArrayList<Passenger>`)
- JUnit Testing -> test reservation service logic
- CSV file handling -> save/load reservations to `src/main/resources`


## Features (Methods)
- Add a passenger to a flight reservation
- Retrieve all passengers booked on a specific flight
- Save reservations to a CSV file
- Load reservations from a CSV file

##To Do

- [x] Domain models
- [x] Repository
- [x] ReservationService 
- [ ] Unit tests
- [ ] CSV files
- [ ] bonus


## Notes




## Learnings

- Use `HashMap` to map flights to passengers
- Check for `null` when working with maps (vs. checking empty)
- Remember for testing -> validate all options, if there is a positive, there must be a negative etc


