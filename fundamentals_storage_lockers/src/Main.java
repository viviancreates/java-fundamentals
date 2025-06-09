import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        //create an instance method
        UserIO io = new UserIO();

        //attach method to the class to print the menu
        io.printMenu();

        //do methods word
        String connect = io.getStringInput("How are you? " );
        System.out.println(connect);

        int number = io.getIntInput("Choose a number...");
        System.out.println(number);
    }

}