package com.example.inventory_manager.command;
import com.example
public class AddOrUpdateProductCommand {
     inventoryIO.displaySectionHeader("Add/Update Inventory Item");
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
}
