package com.example.inventory_manager.command;

import com.example.inventory_manager.model.*;
import com.example.inventory_manager.service.ProductService;
import com.example.inventory_manager.view.ProductIO;
import java.time.LocalDate;
import java.math.BigDecimal;

public class AddOrUpdateProductCommand implements Command {
    private final ProductService productService;
    private final ProductIO productIO;

    public AddOrUpdateProductCommand(ProductService productService, ProductIO productIO) {
        this.productService = productService;
        this.productIO = productIO;
    }

    @Override
    public void execute() {
        printHeader();


        String productId = productIO.getStringInput("Enter product ID: ");
        String productName = productIO.getStringInput("Enter product name: ");
        int quantity = productIO.getIntInput("Enter quantity: ");
        BigDecimal price = productIO.getBigDecimalInput("Enter price: $");

        productIO.displayMessage("Select product type:");
        productIO.displayMessage("1. Physical Game");
        productIO.displayMessage("2. Digital Game");
        productIO.displayMessage("3. Merch");
        productIO.displayMessage("4. Game Perk");

        int typeChoice = productIO.getIntInput("Enter choice (1-4): ");

        Product newProduct = null;

        try {
            switch (typeChoice) {
                case 1:
                    newProduct = createBaseProduct(productId, productName, quantity, price);
                    break;

                case 2:
                    String platform1 = productIO.getStringInput("Enter platform: ");
                    String downloadKey = productIO.getStringInput("Enter download key: ");
                    newProduct = new DigitalGame(productId, productName, quantity, price, platform1, downloadKey);
                    break;

                case 3:
                    String merchType = productIO.getStringInput("Enter merch type: ");
                    String size = productIO.getStringInput("Enter size: ");
                    double weight = productIO.getDoubleInput("Enter weight (oz): ");
                    newProduct = new Merch(productId, productName, quantity, price, merchType, size, weight);
                    break;

                case 4:
                    String perkName = productIO.getStringInput("Enter perk name: ");
                    LocalDate expirationDate = productIO.getDateInput("Enter expiration date (YYYY-MM-DD): ");
                    boolean isTradeable = productIO.getBooleanInput("Is the perk transferable? (yes/no): ");
                    String perkDownloadCode = productIO.getStringInput("Enter perk download key: ");
                    newProduct = new GamePerk(productId, productName, quantity, price, perkName, expirationDate, isTradeable, perkDownloadCode);
                    break;

                default:
                    productIO.displayError("Invalid type");
                    break;
            }

            Product existingProduct = productService.getProduct(productId);
            productService.addOrUpdateProduct(newProduct);

            if (existingProduct != null) {
                productIO.displayMessage("Product updated successfully");
            } else {
                productIO.displayMessage("Product added successfully");
            }
        } catch (Exception e) {
            productIO.displayError("Failed to add or update product: " + e.getMessage());
        }
    }

    private Product createBaseProduct(String productId, String productName, int quantity, BigDecimal price) {
        Product newProduct;
        String platform = productIO.getStringInput("Enter platform: ");
        String storeLocation = productIO.getStringInput("Enter store location: ");
        String condition = productIO.getStringInput("Enter condition: ");
        newProduct = new PhysicalGame(productId, productName, quantity, price, platform, storeLocation, condition);
        return newProduct;
    }

    private void printHeader() {
        productIO.displayMessage("===========================");
        productIO.displayMessage("   Add or Update Product   ");
        productIO.displayMessage("===========================");
    }
}
    /*
     inventoryIO.displaySectionHeader("Add/Update Inventory Item");

        String isbn = inventoryIO.getStringInput("Enter ISBN: ");
        if (isbn == null) return; // User cancelled

        String title = inventoryIO.getStringInput("Enter book title: ");
        if (title == null) return; // User cancelled

        String author = inventoryIO.getStringInput("Enter author: ");
        if (author == null) return; // User cancelled

        String genre = inventoryIO.getStringInput("Enter genre: ");
        if (genre == null) return; // User cancelled

        Integer quantity = inventoryIO.getIntegerInput("Enter quantity: ");
        if (quantity == null) return; // User cancelled

        BigDecimal price = inventoryIO.getBigDecimalInput("Enter price: $");
        if (price == null) return; // User cancelled

        try {
            // Create a new Book record
            Book book = new Book(isbn, title, author, genre);

            // Create a new InventoryItem
            InventoryItem item = new InventoryItem(book, quantity, price);

            // Check if item already exists, this will tell us if this is an add or update
            InventoryItem existingItem = inventoryService.getItem(isbn);

            inventoryService.updateOrAddItem(item);

            if (existingItem != null) {
                inventoryIO.displaySuccess("Item updated successfully!");
            } else {
                inventoryIO.displaySuccess("Item added successfully!");
            }
        } catch (Exception e) {
            inventoryIO.displayError("Failed to add/update item: " + e.getMessage());
        }
    }
     */

