import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        //delete scanner here and bring it in from user io which we did below
        // Scanner console = new Scanner(System.in);
        //create an instance method
        //doing this allows us to not call scanner eaxh time
        UserIO io = new UserIO();

        //moved outside loop bc do not want to create new lockers each time
        LockerService lockerService = new LockerService(5);

        while(true) {
            //attach method to the class to print the menu
            io.printMenu();


            //prompt for user choice
            int choice = io.getIntInput("Enter the menu option: ");

            if (choice == 1) {
                lockerService.rentLocker();
            } else if (choice == 2) {
                lockerService.accessLocker();
            } else if (choice == 3) {
                lockerService.releaseLocker();
            } else {
                io.print("Invalid choice. Pick again pls.");
                break;
            }

        }
    }

}