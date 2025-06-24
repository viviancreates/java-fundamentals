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
                        //FIX pull this put bc it is repeated ALOT
                        for (int i = 0; i < scs.getItems().size(); i++) {
                            Item item = scs.getItems().get(i);
                            io.displayMessage((i + 1)+ ". " + item.getName() + " for $ " + item.getPrice());

                        }

                        //need this first break otherwise, java will keep executing the next cases, even if conditions do not match
                        //this is called fall through
                        //also do not want it here, want it after the case, not just inside else,
                        //break;
                    }
                    //add break here,
                    break;

                case 2:

                    for (int i = 0; i < scs.getItems().size(); i++) {
                        Item item = scs.getItems().get(i);
                        io.displayMessage((i + 1)+ ". " + item.getName() + " for $ " + item.getPrice());
                    }

                    int indexToRemove = io.getIntInput("Enter the item number you want to remove: ");

                   try {
                       //this is making a variable of type Item to store the result in removedItemn -> ITem is the class I defined
                       // I am expecting the method removeItem from scs class to return an ITEM OBJECT
                       //REMEMVER THIS in scs
                       /*
                       public Item removeItem(int index) {
                           return items.remove(index);
                       }
                       */
                        Item removedItem = scs.removeItem(indexToRemove - 1);
                        io.displayMessage("Item removed: " + removedItem.getName());
                    } catch (IndexOutOfBoundsException exception) {
                        io.displayMessage("The item number entered does not exist.");
                    }
                    break;


                case 3:
                    /*
                      public void addItem(String name, double price) {
                        items.add(new Item(name, price));
                      }
                     */
                    String itemNameToAdd = io.getStringInput("Enter the item name you want to add: ");
                    double priceToAdd = io.getDoubleInput("Enter the price of the item: ");
                    scs.addItem(itemNameToAdd, priceToAdd);
                    io.displayMessage("A(n) " + itemNameToAdd.toLowerCase() + " was added to the cart for $" + priceToAdd);
                    break;

                case 4:
                    /*
                    public double getTotal() {
                    double total = 0;
                    for (Item item : items) {
                        total += item.getPrice();
                    }
                    return total;
                }
                     */
                    io.displayMessage("Your final shopping cart has the items: ");
                    for (int i = 0; i < scs.getItems().size(); i++) {
                        Item item = scs.getItems().get(i);
                        io.displayMessage((i + 1)+ ". " + item.getName() + " for $ " + item.getPrice());
                    }
                    String confirm = io.getStringInput("Are you sure you are ready to checkout? ");
                    if (confirm.equalsIgnoreCase("yes")) {
                        double total = scs.getTotal();
                        io.displayMessage("Your total is: $" + String.format("%.2f", total));
                    } else {
                        io.displayMessage("You are returning to the Main Menu.");
                    }
                    break;


                    case 5:
                    io.displayMessage("You are exiting the shopping cart. Thank you.");
                    running = false;
                    break;
                    }
            }



        }


    }

