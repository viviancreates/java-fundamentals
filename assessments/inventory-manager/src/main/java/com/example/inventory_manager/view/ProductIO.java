package com.example.inventory_manager.view;

import com.example.inventory_manager.model.DigitalGame;
import com.example.inventory_manager.model.GamePerk;
import com.example.inventory_manager.model.PhysicalGame;
import com.example.inventory_manager.model.Merch;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.List;
import java.math.BigDecimal;

@Component
public class ProductIO {
    private final Scanner console;

    public ProductIO() {
        this.console = new Scanner(System.in);
    }

    public void displayWelcome() {
        System.out.println("=======================================");
        System.out.println("   Welcome to The Inventory Manager!   ");
        System.out.println("=======================================");
        System.out.println();
    }

    public void displayGoodbye() {
        System.out.println();
        System.out.println("Thank you for using the Inventory Management Application!");
        System.out.println("Have a great day!");
        System.out.println();
    }

    public int displayMenuAndGetChoice() {
        System.out.println();
        System.out.println("=== INVENTORY MANAGEMENT MENU ===");
        System.out.println("1. Add/update inventory item");
        System.out.println("2. Remove inventory item");
        System.out.println("3. View inventory item");
        System.out.println("4. View all inventory items");
        System.out.println("5. Quit");
        System.out.println();
        return getIntegerInputWithDefault("Please select an option (1-5): ", -1);
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return console.nextLine();
    }

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
