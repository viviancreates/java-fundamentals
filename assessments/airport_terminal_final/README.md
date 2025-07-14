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

## To Do

- [x] Domain models
  - [] Refactor - do not want to use default values
- [x] Repository
- [x] ReservationService 
- [ ] Unit tests
- [x] CSV files
- [ ] Bonus

- [ ] Aircraft type and model the same...
- [ ] Give flights to choose from, should not enter aircraft type and model... think about flow

## Notes
- Edit setters
- **Ternary**
  - The ternary operator is a mini if else statement in a single line
```
  condition ? valueIfTrue : valueIfFalse
 ```
- if the condition is true, use valueiftrue
- if the condition is false, use valueiffalse

```
// EXAMPLE 
if (x > y) {
   print("bigger");
} else {
   print("smaller);
}

// same as

x > y ? print("bigger") : print("smaller");

//? is ternary, it's one line if else shortcut

```

## Learnings
- Currently just replacing the csv -> need to append, fix the save to file
```
//overwrites
 private void saveToFile() {
        //add a reservation and save - try with resources
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            //loop through each entry in the map
            //- key is flight number
            //-value is ArrayList<reservation>
            for (ArrayList<Reservation> reservationList : reservations.values()) {

                for (Reservation reservation : reservationList) {

                    //pull out flight and passenger objects from reservation
                    Flight flight = reservation.getFlight();
                    Passenger passenger = reservation.getPassenger();
                    Aircraft aircraft = flight.getAircraft();

                    //write to the csv
                    writer.printf("%s,%s,%.2f,%s,%s,%s,%s%n",
                            //flightnumber
                            flight.getFlightNumber(),
                            //departuredate
                            flight.getDepartureDate(),
                            //ticketprice
                            flight.getTicketPrice(),
                            //passengerName or None -> if the passenger is null or passenger is empty is true, write none, otherwise get name
                            (passenger == null || passenger.isEmpty()) ? "None" : passenger.getName(),
                            //passportNumber or None
                            (passenger == null || passenger.isEmpty()) ? "None" : passenger.getPassportNumber(),
                            //aircraftModel
                            aircraft.getModel(),
                            //aircraftTye
                            aircraft.getType()
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + filename, e);
        }
```
---

- Changes in service, due to repo -> the repo tells data what to do
  - FILE HANDLING CODE -> reads and writes csv
- the service holds business logic and coordinates domain operations
  - BUSINESS RULES -> a passenger needs a passport number and a name
    - can validate passenger data befor saving
    - can check if a seat is even abilable

**Repository** where the data lives
**Service** where the logic lives
**View** where user flow lives
---

- Use `HashMap` to map flights to passengers
- Check for `null` when working with maps (vs. checking empty)
- Remember for testing -> validate all options, if there is a positive(happy outcome), there must be a negative (invalid outcome)etc
  - Especially for validations
- Different data relationships affect structure

- Bookstore Pattern
  - Each CSV row represents one book and its inventory information 
  - Uses a Book class plus an InventoryItem class
  - A single map (Map<String, InventoryItem>) is enough because it wraps the Book plus its data

- Airport Terminal
  - Each CSV row represents one reservation: flight details plus passenger
  - Instead of managing two separate maps (Flight and List<Passenger>), it is clearer to use a Reservation class that holds both
  - A single List<Reservation> matches one line per reservation -> this keeps the Flight and Passenger classes simple and reusable

- Shopping Cart Insight
  - Code review remark - two separate HashMaps made the design more complicated
  - A better pattern would have been to create a Cart model and wraps Item plus quantity in one object
    - ...then, one list or map is enough

- If each row in CSV naturally maps to one logical thing, then model that thing with its own class
  - This keeps data structures simple
  - Keep it simple and wrap related pieces of data together if they always belong together
    - ...easier to test, avoids confusing multiple maps(shopping cart assessment -> had to update both maps)

## Questions
- Is it better to put the validation in the model or csvrepo - is the passenger existing -> none