package com.example.inventory_manager;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTests {
    private InventoryRepository inventoryRepository;
    private CartService cartService;

    // Test data
    private Book book1;
    private Book book2;
    private InventoryItem inventoryItem1;
    private InventoryItem inventoryItem2;

    @BeforeEach
    void setUp() {
        // Create fresh instances for each test
        inventoryRepository = new InMemoryInventoryRepository();
        cartService = new CartService(inventoryRepository);

        // Create test books
        book1 = new Book("978-0132350884", "Clean Code", "Robert Martin", "Technology");
        book2 = new Book("978-0321125217", "Domain-Driven Design", "Eric Evans", "Technology");

        // Create test inventory items
        inventoryItem1 = new InventoryItem(book1, 10, new BigDecimal("29.99"));
        inventoryItem2 = new InventoryItem(book2, 5, new BigDecimal("45.50"));

        // Add items to inventory
        inventoryRepository.add(inventoryItem1);
        inventoryRepository.add(inventoryItem2);
    }
}
