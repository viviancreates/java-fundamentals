package org.example.ui;

import java.util.Scanner;

public class TerminalUtils {

    Scanner console = new Scanner(System.in);
    //display(Menu)
    public void displayMenu() {
       System.out.println("Main Menu");
       System.out.println("1. Display Cart");
       System.out.println("2. Add an Item");
       System.out.println("3. Remove an Item");
       System.out.println("4. Checkout");
       System.out.println("5. Exit");
    }

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

    //getDouble(prompt);
    public double getDoubleInput(String prompt) {
        System.out.println(prompt);
        String input = console.nextLine();
        return Double.parseDouble(input);
    }

    //displayMessage(message);
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
