import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        //create an instance method
        //doing this allows us to not call scanner eaxh time
        UserIO io = new UserIO();

        //attach method to the class to print the menu
        io.printMenu();



        //prompt for user choice
        System.out.println("Choose Menu option: ");
        int choice = io.getIntInput("Enter the option: ");

        LockerService lockerService = new LockerService(5);
        lockerService.printLockerStatus();

        //reminder to break the loop
        if (choice == 1) {
            lockerService.rentLocker();
        }else if(choice == 2){
            lockerService.accessLocker();
        }else if(choice == 3){
            lockerService.releaseLocker();
        }else{
            System.out.println("Invalid choice. Pick again pls.");
        }
    }

}