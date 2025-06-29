package org.example.command;
import org.example.inventory.Inventory;
import org.example.model.Item;

import org.example.model.Item;
import org.example.service.ShoppingCartService;
import org.example.ui.TerminalUtils;

public class AddItemCommand implements Command {
    private final ShoppingCartService scs;
    private final Inventory inventory;
    private final TerminalUtils io;

    public AddItemCommand(ShoppingCartService scs, Inventory inventory, TerminalUtils io) {
        this.scs = scs;
        this.inventory = inventory;
        this.io = io;
    }

    @Override
    public void execute() {
        inventory.displayInventory();
        String inventoryChoice = io.getStringInput("Enter the name of the item to add to cart: ");

        if (inventory.hasItem(inventoryChoice)) {
            org.example.model.Item selectedItem = inventory.getItem(inventoryChoice);
            int quantity = io.getIntInput("Enter the quantity of the item you want to add: ");
            for (int i = 0; i < quantity; i++) {
                scs.addItem(selectedItem);
            }
            io.displayMessage(selectedItem.getName() + " added to the cart.");
        } else {
            io.displayMessage("Item is not available.");

        }
    }
}
