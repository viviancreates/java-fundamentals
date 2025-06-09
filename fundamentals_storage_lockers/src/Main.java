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

        //do methods work
        String connect = io.getStringInput("How are you? " );
        System.out.println(connect);

        int number = io.getIntInput("Choose a number...");
        System.out.println(number);

        LockerService lockerService = new LockerService(5);
        lockerService.printLockerStatus();
    }

}