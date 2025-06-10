import java.util.Scanner;

public class LockerService {
    //create an array
    //this is to hold locker objects created
    private Locker[] lockers;
    Scanner console = new Scanner(System.in);


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

    public void printLockerStatus() {
        for (int i = 0; i < lockers.length; i++) {
            System.out.println("Locker " + (i + 1) );
            //create the status variable(not needed for me)
            String status = "";
            if (lockers[i].isRented()){
                status = "rented";
            }else{
                status = "available";
            }
            System.out.println("Locker " + (i + 1) + "is" + status);
        }
    }


    public void rentLocker() {
        System.out.println("say hello and rent");
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

                System.out.println("Locker rented!");
                System.out.println("Your locker number: " + lockerNumber);
                System.out.println("Your PIN: " + pin);
                return;
            }
        }
    }

    public void accessLocker() {
        System.out.println("say hello and access");
        //Prompt user for locker number and PIN
        //Validate input against stored data
        //Grant access if match is found, else show error

        //prompting user for locker and pin
        System.out.print("Enter your locker number: ");
        String number = console.nextLine();

        System.out.print("Enter your PIN: ");
        String pin = console.nextLine();

        //loop to go through all the lockers and match locker number and pin
        for (int i = 0; i < lockers.length; i++) {
            Locker locker = lockers[i];

            if (locker.getLockerNumber().equals(number)) {
                if (locker.checkPin(pin)) {
                    System.out.println("Access granted!");
                } else {
                    System.out.println("Wrong PIN.");
                }
                return;
            }
        }

        System.out.println("Locker not found.");
    }

    public void releaseLocker() {
        System.out.println("say hello and release");
        //Prompt for locker number and PIN
        //Confirm action: “Are you sure?” (Yes/No)
        //Clear data from array if confirmed
        //Show error for incorrect PIN or locker

        System.out.print("Enter your locker number: ");
        String number = console.nextLine();

        System.out.print("Enter your PIN: ");
        String pin = console.nextLine();

        for (int i = 0; i < lockers.length; i++) {
            Locker locker = lockers[i];
            if (locker.getLockerNumber().equals(number)) {
                if (locker.checkPin(pin)) {
                    //confirm
                    System.out.print("Are you sure? (yes/no): ");
                    String confirm = console.nextLine();
                    if (confirm.equalsIgnoreCase("yes")) {
                       //release the locker -> pulled from locker class
                        locker.releaseLocker();
                        System.out.println("Locker released.");
                    } else {
                        System.out.println("Locker not released");
                    }
                } else {
                    System.out.println("Wrong PIN number.");
                }
                return;
            }
        }

        System.out.println("Locker not found.");
    }
}

