public class LockerService {
    //create an array
    //this is to hold locker objects created
    private Locker[] lockers;

    UserIO io = new UserIO();


    //create a constructor -> fill the array up
    //initialize the array of lockers
    public LockerService(int size) {
        lockers = new Locker[size];

        // fill array with empty lockers -> the array exists and holds locker objects
        for (int i = 0; i < size; i++) {
            //so now locker[0], locker [1], locker[2] are all objects being created...
            lockers[i] = new Locker();
        }
    }

    public void rentLocker() {
        //REMINDER grabbing from io print void method
        io.print("say hello and rent");
        //Assign next available locker number
        //Generate and display a 4-digit PIN (as a String with leading zeros)
        //Store locker and PIN in array
        //Remove option if no lockers available

        for (int i = 0; i < lockers.length; i++) {
            //if the locker is available
            if (!lockers[i].isRented()) {
                //assign a random pin
                String pin = String.format("%02d", (int) (Math.random() * 10000));
                //assign the locker number
                String lockerNumber = String.valueOf(i + 1);
                //tell the locker objecect locker[i] that its rented
                lockers[i].rentLocker(pin, lockerNumber);

                io.print("Locker rented!");
                io.print("Your locker number: " + lockerNumber);
                io.print("Your PIN: " + pin);
                return;
            }
        }
        io.print("No available lockers.");
    }

    public void accessLocker() {
        io.print("say hello and access");
        //Prompt user for locker number and PIN
        //Validate input against stored data
        //Grant access if match is found, else show error

        //prompting user for locker and pin
        //System.out.print("Enter your locker number: ");
        //String number = console.nextLine();
        //USE THE IO get string input
        String number = io.getStringInput("Enter your locker number: ");

        //SAME
        //System.out.print("Enter your PIN: ");
        //String pin = console.nextLine();
        String pin = io.getStringInput("Enter your PIN: ");

        //loop to go through all the lockers and match locker number and pin
        for (int i = 0; i < lockers.length; i++) {
            Locker locker = lockers[i];

            if (locker.getLockerNumber().equals(number)) {
                if (locker.checkPin(pin)) {
                    io.print("Access granted!");
                } else {
                    io.print("Wrong PIN.");
                }
                return;
            }
        }

        io.print("Locker not found.");
    }

    public void releaseLocker() {
        io.print("say hello and release");
        //Prompt for locker number and PIN
        //Confirm action: “Are you sure?” (Yes/No)
        //Clear data from array if confirmed
        //Show error for incorrect PIN or locker

        String number = io.getStringInput("Enter your locker number: ");

        String pin = io.getStringInput("Enter your PIN ");

        for (int i = 0; i < lockers.length; i++) {
            Locker locker = lockers[i];
            if (locker.getLockerNumber().equals(number)) {
                if (locker.checkPin(pin)) {
                    //confirm
                    String confirm = io.getStringInput("Are you sure? (yes/no): ");
                    if (confirm.equalsIgnoreCase("yes")) {
                       //release the locker -> pulled from locker class
                        locker.releaseLocker();
                        io.print("Locker released.");
                    } else {
                        io.print("Locker not released");
                    }
                } else {
                    io.print("Wrong PIN number.");
                }
                return;
            }
        }

        io.print("Locker not found.");
    }
}

