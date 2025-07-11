package com.airport.view;

import com.airport.domain.model.Reservation;
import java.util.List;

import java.util.Scanner;

import java.math.BigDecimal;


public class TerminalIO {
    private final Scanner console;

    public TerminalIO() {
        this.console = new Scanner(System.in);
    }

    public void displayWelcome() {
        System.out.println("=======================================");
        System.out.println("    Welcome to The Airport Terminal!");
        System.out.println("=======================================");
        System.out.println();
    }

    public void displayGoodbye() {
        System.out.println();
        System.out.println("Thank you for using the Airport Terminal Management!");
        System.out.println("Have a great day!");
        System.out.println();
    }

    public int displayMenuAndGetChoice() {
        System.out.println();
        System.out.println("=== AIRPORT TERMINAL MENU ===");
        System.out.println("1. Create Reservation");
        System.out.println("2. View All Reservations");
        System.out.println("3. Quit");
        System.out.println();

        return getIntegerInputWithDefault("Please select an option (1-4): ", -1);
    }

    //getString(prompt);
    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return console.nextLine();
    }
    //getInt(prompt);
    public int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = console.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    //displayMessage(message);
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public BigDecimal getBigDecimalInput(String prompt) {
        System.out.print(prompt);
        String input = console.nextLine().trim();

        if (input.isEmpty()) {
            displayError("Input cannot be empty. Please try again.");
            return getBigDecimalInput(prompt);
        }

        try {
            BigDecimal value = new BigDecimal(input);
            if (value.compareTo(BigDecimal.ZERO) <= 0) {
                displayError("Please enter a positive price.");
                return getBigDecimalInput(prompt);
            }
            return value;
        } catch (NumberFormatException e) {
            displayError("Please enter a valid price (e.g., 19.99).");
            return getBigDecimalInput(prompt);
        }
    }

    private int getIntegerInputWithDefault(String prompt, int defaultValue) {
        System.out.print(prompt);
        String input = console.nextLine().trim();

        if (input.isEmpty()) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            displayError("Please enter a valid number.");
            return getIntegerInputWithDefault(prompt, defaultValue);
        }
    }

    public void displayError(String message) {
        System.out.println("✗ ERROR: " + message);
    }

}
