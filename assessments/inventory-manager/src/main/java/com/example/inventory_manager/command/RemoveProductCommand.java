package com.example.inventory_manager.command;

import com.example.inventory_manager.model.*;
import com.example.inventory_manager.service.ProductService;
import com.example.inventory_manager.view.ProductIO;
import java.time.LocalDate;
import java.math.BigDecimal;

public class RemoveProductCommand implements Command {
    private final ProductService productService;
    private final ProductIO productIO;

    public RemoveProductCommand(ProductService productService, ProductIO productIO) {
        this.productService = productService;
        this.productIO = productIO;
    }

    @Override
    public void execute() {
        printHeader();

        String productId = productIO.getStringInput("Enter the product ID to remove: ");

        if (productId.isEmpty()) {
            productIO.displayError("No valid prouct ID entered.");
            return;
        }

        try {
        productService.removeProduct(productId);
        productIO.displayMessage("Product removed.");

        } catch (IllegalArgumentException e) {
            productIO.displayError("Failed to remove product: " + e.getMessage());
        }

    }

    private void printHeader() {
        productIO.displayMessage("===========================");
        productIO.displayMessage("       Remove Product      ");
        productIO.displayMessage("===========================");
    }
}
