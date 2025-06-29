package org.example.command;
import org.example.inventory.Inventory;
import org.example.model.Item;
import org.example.service.ShoppingCartService;
import org.example.ui.TerminalUtils;

public class CheckoutCommand implements Command {
    private final ShoppingCartService scs;
    private final Inventory inventory;
    private final TerminalUtils io;

    public CheckoutCommand(ShoppingCartService scs, Inventory inventory, TerminalUtils io) {
        this.scs = scs;
        this.inventory = inventory;
        this.io = io;

    }

    @Override
    public void execute() {
        io.displayMessage("Your final shopping cart has the items: ");
        for (int i = 0; i < scs.getItems().size(); i++) {
            Item item = scs.getItems().get(i);
            io.displayMessage((i + 1) + ". " + item.getName() + " for $ " + item.getPrice());
        }
        String confirm = io.getStringInput("Are you sure you are ready to checkout? (yes/no) ");
        if (confirm.equalsIgnoreCase("yes")) {
            double total = scs.getTotal();
            io.displayMessage("Your total is: $" + String.format("%.2f", total));
        } else {
            io.displayMessage("You are returning to the Main Menu.");
        }

    }
}
