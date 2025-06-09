import java.util.Scanner;

public class Main {
    //all methods start with an access modifier
    //public means we can access the method outside this class
    //void indicates this method will not return any values
    //give the method a name and the empty() means the method does not need any inputs
    public static void printWelcomeMessage() {
        System.out.println("==== Welcome to the Theater ====");
        System.out.println("Please enter the ticket info below.\n");
    }

    //write a method to prompt for a string
    //return type is string
    //accepts two inputs -> string representing prompt to be printed to the user and instance of scanner
    public static String getString(String prompt, Scanner console){
        System.out.print(prompt);
        return console.nextLine();
    }

    //getString with 3 values
    public static String getString(String prompt, Scanner console, String val1, String val2, String val3){
        boolean isValid = false;
        String input = "";
        while (!isValid) {
            input = getString(prompt, console);
            if (input.equalsIgnoreCase(val1) ||
                    input.equalsIgnoreCase(val2) ||
                    input.equalsIgnoreCase(val3)) {
                isValid = true;
            }else{
                System.out.println("Invalid entry. Please try again.");
            }
        }
        return input;
    }

    //write a method to prompt for a whole number
    //return type is int
    //accepts two inputs -> string representing prompt, instance of scanner
    public static int getInt(String prompt, Scanner console){
        System.out.print(prompt);
        String input = console.nextLine();
        return Integer.parseInt(input);
    }

    //write a method to print purchase summary
    //return type is void
    //accepts inputs for movie name, movie time, number of adult tickets, number of child tickets, and total cost
    public static void printPurchaseSummary(String movie, String movieTime, int adultTix, int childTix, double totalCost){
        System.out.println("\nPurchase Complete! Summary: ");
        System.out.println("Movie:\t\t" + movie);
        System.out.println("Time:\t\t" + movieTime);
        System.out.println("Adult Tix:\t" + adultTix);
        System.out.println("Child Tix:\t" + childTix);
        System.out.println("Total Cost:\t$" + totalCost);
    }

    //main method
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        //call the method
        printWelcomeMessage();

        // Prompt user for movie title
        //System.out.print("What movie would you like to see? ");
        //String movie = console.nextLine();
        //these lines above turn into -> instead, call to getString will capture movie title in the variable movie
        String movie = getString("What movie would you like to see? ", console);

        // Prompt user for movie time (1pm, 2:30pm, etc.)
        //System.out.print("There are 3 matinees available: 1pm, 2:30pm, 4pm\nWhat time?");
        //String movieTime = console.nextLine();
        String movieTime = getString("There are 3 matinees available: 1pm, 2:30pm, 4pm\nWhat time? ",
                console, "1pm", "2:30pm", "4pm");

        // Prompt user for # of adult tickets
        //System.out.print("# of Adult Tickets? ");
        //String adultTixString = console.nextLine();
        //int adultTix = Integer.parseInt(adultTixString);
        int adultTix = getInt("# of Adult Tickets? ", console);

        // Prompt user for # of child tickets
        //System.out.print("# of Child Tickets? ");
        //String childTixString = console.nextLine();
        //int childTix = Integer.parseInt(childTixString);
        int childTix = getInt("# of Child Tickets? ", console);

        // Calculate cost: Matinee Adult: $11.75, Child: $8.25
        double totalCost = adultTix * 11.75 + childTix * 8.25;

        //moved purchase summary
        printPurchaseSummary(movie, movieTime, adultTix, childTix, totalCost);


        System.out.println("\nThanks!  Enjoy the show!");

    }
}