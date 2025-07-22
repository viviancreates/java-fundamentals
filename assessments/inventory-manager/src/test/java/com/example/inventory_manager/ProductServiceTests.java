package com.example.inventory_manager;

import com.example.inventory_manager.model.DigitalGame;
import com.example.inventory_manager.model.PhysicalGame;
import com.example.inventory_manager.model.GamePerk;
import com.example.inventory_manager.model.Merch;
import com.example.inventory_manager.model.Product;
import com.example.inventory_manager.repository.*;
import com.example.inventory_manager.service.ProductService;

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
    private ProductRepository productRepository;
    private ProductService productService;

    // Test data
    private PhysicalGame physicalGame;
    private DigitalGame digitalGame;
    private Merch merch;
    private GamePerk gamePerk;

    @BeforeEach
    void setUp() {

        productRepository = new InMemoryProductsRepository();
        productService = new ProductService(productRepository);
    }

    @Test
    @DisplayName("Add a product to the inventory")
    void addProductToInventorySuccessfully() {
        PhysicalGame newGame = new PhysicalGame( "0101", "Test Game", 2, new BigDecimal("10.00"), "Computer", "Austin", "Used");
        //add product to the inventory
        productService.addOrUpdateProduct(newGame);
        Product actual = productService.getProduct("0101");

        // Assert base fields
        assertEquals("0101", actual.getProductId());
        assertEquals("Test Game", actual.getProductName());
        assertEquals(2, actual.getQuantity());
        assertEquals(new BigDecimal("10.00"), actual.getPrice());

        // Use getProductType() instead of instanceof
        assertEquals("PhysicalGame", actual.getProductType());

        // Safe to cast now
        PhysicalGame actualGame = (PhysicalGame) actual;

        // Assert subclass fields
        assertEquals("Computer", actualGame.getPlatform());
        assertEquals("Austin", actualGame.getStoreLocation());
        assertEquals("Used", actualGame.getCondition());

        /*
        String expectedId = "0101";
        String expectedName = "Test Game";
        int expectedQuantity = 2;
        BigDecimal expectedPrice = new BigDecimal("10.00");
        String expectedPlatform = "Computer";
        String expectedStoreLocation = "Austin";
        String expectedCondition = "Used";

        Product actualProductAddedToInventory = productService.getProduct("0101");

        assertEquals(expectedId, actualProductAddedToInventory.getProductId());

         */

    }

    /*
    @Test
    @DisplayName("Delete a product from inventory")

    @Test
    @DisplayName("Add multiple products into inventory")

    @Test
    @DisplayName("Find product in the inventory by ID")

    @Test
    @DisplayName("Show all products in stock")

    @Test
    @DisplayName("Add a product by ID and returns failure")

    @Test
    @DisplayName("Add a product to inventory using empty ID and returns failure")

    @Test
    @DisplayName("Search for product by ID and return failure)

     */
}
