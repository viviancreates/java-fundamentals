import java.util.Scanner;

public class UserIO {
    Scanner console = new Scanner(System.in);

    //print the menu
    public static void printMenu() {
        System.out.println("Welcome to Vivian's Locker Service!");
        System.out.println("\nWhat would you like to do next?");
        System.out.println("1. Rent a Locker");
        System.out.println("2. Access a Locker");
        System.out.println("3. Release a Locker");
        System.out.println("---");
        System.out.println("Any other key to exit.");

        //reminder to add validation to printmenu
    }

    //method prints a prompt to user, rec user input as string string
    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return console.nextLine();
    }

    //method prints a prompt to user, rec user input as string and converts to integer
    public int getIntInput(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(console.nextLine());
    }
}
