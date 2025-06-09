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

    //print choice
    public static void displayChoices(String[] choices) {
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ". " + choices[i]);
        }
    }

    //rent a locker
    //create e method to give a user one of these lockers


    //access a locker

    //release a locker

    //create a pin

}
