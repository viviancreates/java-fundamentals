package org.example.command;

import org.example.inventory.Inventory;
import org.example.model.Item;

import org.example.model.Item;
import org.example.service.ShoppingCartService;
import org.example.ui.TerminalUtils;

public class DisplayCartCommand implements Command{
    private final ShoppingCartService scs;
    private final Inventory inventory;
    private final TerminalUtils io;

    public DisplayCartCommand(ShoppingCartService scs, Inventory inventory, TerminalUtils io) {
        this.scs = scs;
        this.inventory = inventory;
        this.io = io;
    }

    @Override
    public void execute() {
        if (scs.getItems().isEmpty()){
            io.displayMessage("Your cart is empty");

        } else {
            io.displayMessage("Your cart contains the following items: ");
            //FIX pull this put bc it is repeated ALOT
            for (int i = 0; i < scs.getItems().size(); i++) {
                Item item = scs.getItems().get(i);
                int quantity = scs.getItemQuantity().get(item.getName());
                io.displayMessage((i + 1) + ". " + "(" + quantity + ") " + item.getName() + " for $ " + item.getPrice());

            }
        }

}
