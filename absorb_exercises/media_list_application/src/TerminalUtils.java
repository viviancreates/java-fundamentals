import java.util.Scanner;

public class TerminalUtils {
    Scanner console = new Scanner(System.in);

    //displayMenu();
    public static void displayMenu() {
        System.out.println("=== Media List Application ===");
        System.out.println("1. Add Media");
        System.out.println("2. Remove Media");
        System.out.println("3. Play Media");
        System.out.println("4. List All Media");
        System.out.println("5. Quit");
        System.out.println("Choose an option: ");
        //reminder to add validation to printmenu
    }

    //getMenuChoice();

    //getString(prompt);
    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return console.nextLine();
    }
    //getInt(prompt);
    public int getIntInput(String prompt) {
        System.out.print(prompt);
        String input = console.nextLine();
        return Integer.parseInt(input);
    }

    //displayMessage(message);
    public void displayMessage(String message) {
        System.out.println(message);
    }

    //displayMediaList(mediaList);

}
