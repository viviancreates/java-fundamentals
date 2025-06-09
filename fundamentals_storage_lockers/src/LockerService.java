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


    public void rentLocker() {
        System.out.println("say hello and rent");
        //Assign next available locker number
        //Generate and display a 4-digit PIN (as a String with leading zeros)
        //Store locker and PIN in array
        //Remove option if no lockers available
    }

    public void accessLocker() {
        System.out.println("say hello and access");
        //Prompt user for locker number and PIN
        //Validate input against stored data
        //Grant access if match is found, else show error
    }

    public void releaseLocker() {
        System.out.println("say hello and release");
        //Prompt for locker number and PIN
        //Confirm action: “Are you sure?” (Yes/No)
        //Clear data from array if confirmed
        //Show error for incorrect PIN or locker
    }

}
