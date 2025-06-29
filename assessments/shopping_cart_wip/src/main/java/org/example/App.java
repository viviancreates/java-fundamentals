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
        //create the service
        ShoppingCartService scs = new ShoppingCartService();
        //add io
        TerminalUtils io = new TerminalUtils();
        //add while loop -> make easier to exit loop, also easier to add condition if cart is empty
        boolean running = true;

        ItemFactory itemFactory = new ItemFactory();

        Inventory inventory = new Inventory(itemFactory); //load items

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
                        //need this first break otherwise, java will keep executing the next cases, even if conditions do not match
                        //this is called fall through
                        //also do not want it here, want it after the case, not just inside else,
                        //break;

                    //add break here,
                    break;

                case 2:
                    /*
                      public void addItem(String name, double price) {
                        items.add(new Item(name, price));
                      }
                     */
                    //String itemNameToAdd = io.getStringInput("Enter the item name you want to add: ");
                    //double priceToAdd = io.getDoubleInput("Enter the price of the item: ");
                    //int quantityToAdd = io.getIntInput("Enter the quantity of the item you want to add: ");

                    //--------- MOVED EVERYTHING OT ADD COMMAND and reolace
                    addItemCommand.execute();
                    break;
//                    inventory.displayInventory();
//                    String inventoryChoice = io.getStringInput("Enter the name of the item to add to cart: ");
//
//                    if (inventory.hasItem(inventoryChoice)) {
//                        Item selectedItem = inventory.getItem(inventoryChoice);
//                        int quantity = io.getIntInput("Enter the quantity of the item you want to add: ");
//
//                        for (int i = 0; i < quantity; i++) {
//                        //for (int i = 0; i < quantityToAdd; i++) {
//                            //Item newItem = itemFactory.createItem(itemNameToAdd, priceToAdd);
//                            //scs.addItem(newItem);
//                            scs.addItem(selectedItem);
//                        }
//                        io.displayMessage(selectedItem.getName() + " added to the cart.");
//                    } else {
//                        io.displayMessage("Item is not available.");
//                    }






                    //i am refactoring to use the item factory
                    //scs.addItem(itemNameToAdd, priceToAdd);
                    //wrap this in a for loop
                    //Item newItem = itemFactory.createItem(itemNameToAdd, priceToAdd);
                    //scs.addItem(newItem);
                    //for (int i = 0; i < quantityToAdd; i++) {
                      //  Item newItem = itemFactory.createItem(itemNameToAdd, priceToAdd);
                        //scs.addItem(newItem);
                    //}
                    //io.displayMessage("A(n) " + itemNameToAdd.toLowerCase() + " was added to the cart for $" + priceToAdd);
//                  break;

                case 3:

                    removeItemCommand.execute();
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

