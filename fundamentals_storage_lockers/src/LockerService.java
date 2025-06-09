public class LockerService {
    //create an array
    //this is to hold locker objects created
    private Locker[] lockers;

    //create a constructor -> fill the array up
    //initialize the array of lockers
    public LockerService(int size) {
        lockers = new Locker[size];

        // fill array with empty lockers -> the array exists and holds locker objects
        for (int i = 0; i < size; i++) {
            lockers[i] = new Locker();
        }
    }

    public void printLockerStatus() {
        for (int i = 0; i < lockers.length; i++) {
            System.out.println("Locker " + (i + 1) );
        }
    }

    //rent a locker

    //access a locker

    //release a locker

    //create a pin

}
