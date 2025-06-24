package org.example;
import org.example.service.ShoppingCartService;
import org.example.model.Item;
import org.example.ui.TerminalUtils;

public class App {
    public static void main(String[] args) {
        //create the service
        ShoppingCartService scs = new ShoppingCartService();
        //add io
        TerminalUtils io = new TerminalUtils();
        //add while loop -> make easier to exit loop, also easier to add condition if cart is empty
        boolean running = true;

        //why am i adding the variable running why not just say while true
        while(running) {
            io.displayMenu();
            int choice = io.getIntInput("Enter choice: ");

            /*
            REMINDER
            System.out.println("1. Display Cart");
            System.out.println("2. Remove an Item");
            System.out.println("3. Add an Item");
            System.out.println("4. Checkout");
             System.out.println("5. Exit");
             */

            switch (choice) {
                case 1:
                    if (scs.getItems().isEmpty()){
                        io.displayMessage("Your cart is empty");

                    } else {
                        io.displayMessage("Your cart contains the following items: ");
                        for (int i = 0; i < scs.getItems().size(); i++) {
                            Item item = scs.getItems().get(i);
                            io.displayMessage(item.getName() + item.getPrice());

                        }

                        //need this first break otherwise, java will keep executing the next cases, even if conditions do not match
                        //this is called fall through
                        //also do not want it here, want it after the case, not just inside else,
                        //break;
                    }
                    //add break here,
                    break;

                case 2;
                
                case 5:
                    io.displayMessage("You are exiting the shopping cart. Thank you.");
                    running = false;
                    break;
                    }
            }



        }


    }

