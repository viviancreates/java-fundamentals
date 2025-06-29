package org.example;
import org.example.service.ShoppingCartService;
import org.example.model.Item;
import org.example.ui.TerminalUtils;
import org.example.factory.ItemFactory;
import org.example.inventory.Inventory;
import org.example.command.AddItemCommand;
import org.example.command.DisplayCartCommand;
import org.example.command.RemoveItemCommand;
import org.example.command.CheckoutCommand;

public class App {
    public static void main(String[] args) {
        ShoppingCartService scs = new ShoppingCartService();
        TerminalUtils io = new TerminalUtils();
        boolean running = true;
        ItemFactory itemFactory = new ItemFactory();
        Inventory inventory = new Inventory(itemFactory);

        AddItemCommand addItemCommand = new AddItemCommand(scs, inventory, io);
        DisplayCartCommand displayCartCommand = new DisplayCartCommand(scs, inventory, io);
        RemoveItemCommand removeItemCommand = new RemoveItemCommand(scs, inventory, io);
        CheckoutCommand checkoutCommand = new CheckoutCommand(scs, inventory, io);

        while(running) {
            io.displayMenu();
            int choice = io.getIntInput("Enter choice: ");

            switch (choice) {
                case 1:
                    displayCartCommand.execute();
                    break;

                case 2:
                    addItemCommand.execute();
                    break;

                case 3:
                    removeItemCommand.execute();
                    break;

                case 4:
                    checkoutCommand.execute();
                    break;

                case 5:
                    io.displayMessage("You are exiting the shopping cart. Thank you.");
                    running = false;

                case 6:
                    io.displayMessage("Try Again. Please select a choice from the Menu.");
                            break;
                    }
            }
        }
    }

