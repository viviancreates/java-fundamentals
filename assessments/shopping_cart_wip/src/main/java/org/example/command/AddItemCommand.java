package org.example.command;

import org.example.inventory.Inventory;
import org.exampe.model.Item;

import org.example.service.ShoppingCartService;
import org.example.ui.TerminalUtils;

public class AddItemCommand {
    private final ShoppingCartService scs;
    private final Inventory inventory;
    private final TerminalUtils io;

    punlic AddItemComman(ShoppingCartService scs, Inventory inventory, TerminalUtils io) {
        this.scs = scs;
        this.inventory = inventory;
        this.io = io;
    }
    
}
