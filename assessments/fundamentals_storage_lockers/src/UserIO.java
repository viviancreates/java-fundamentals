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

    //add a method to print message
    public void print(String message) {
        System.out.println(message);
    }

    //method prints a prompt to user, rec user input as string string
    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return console.nextLine();
    }

    //method prints a prompt to user, rec user input as string and converts to integer

    public int getIntInput(String prompt) {

        //missing return statement, add while loop -> if parsing fails, catch, print error, loop runs again, re prompt
        while (true) {
            System.out.print(prompt);
            //turn to string to catch the bad input and it doesnt go cray
            //get input first
            String input = console.nextLine();

            //"unreachable statement??? bc i had left return above, once something is returned by methid, code after will not be reached
            try {
                //try to parse the input
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                //if fail, loop again
                System.out.println("Invalid. Please enter a number.");
            }
        }
    }
}

