package org.example.command;
import org.example.inventory.Inventory;
import org.example.model.Item;
import org.example.service.ShoppingCartService;
import org.example.ui.TerminalUtils;

public class RemoveItemCommand implements Command{
    private final ShoppingCartService scs;
    private final Inventory inventory;
    private final TerminalUtils io;

    public RemoveItemCommand(ShoppingCartService scs, Inventory inventory, TerminalUtils io) {
        this.scs = scs;
        this.inventory = inventory;
        this.io = io;
    }

    @Override
    public void execute() {
        for (int i = 0; i < scs.getItems().size(); i++) {
            Item item = scs.getItems().get(i);
            io.displayMessage((i + 1)+ ". " + item.getName() + " for $ " + item.getPrice());
        }

        String nameToRemove = io.getStringInput("Enter the item name you want to remove: ");

        try {
            Item removedItem = scs.removeItem(nameToRemove);
            io.displayMessage("Item removed: " + removedItem.getName());
        } catch (IndexOutOfBoundsException exception) {
            io.displayMessage("The item name entered does not exist.");
        }
    }
}
