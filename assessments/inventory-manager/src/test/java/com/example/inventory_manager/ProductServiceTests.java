package com.example.inventory_manager;

import com.example.inventory_manager.model.DigitalGame;
import com.example.inventory_manager.model.PhysicalGame;
import com.example.inventory_manager.model.GamePerk;
import com.example.inventory_manager.model.Merch;
import com.example.inventory_manager.model.Product;
import com.example.inventory_manager.repository.*;
import com.example.inventory_manager.service.ProductService;
import java.lang.reflect.Field;

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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        File file = new File("data/test.csv");
        if (file.exists()) {
            file.delete();
        }

        //create a new empty file
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Could not reset test CSV file", e);
        }

        productRepository = new CsvProductRepository("data/test.csv");
        productService = new ProductService(productRepository);
    }

    @Test
    @DisplayName("Add a product to the inventory")
    void addProductToInventorySuccessfully() {
        PhysicalGame newGame = new PhysicalGame( "0101", "Test Game", 2, new BigDecimal("10.00"), "Computer", "Austin", "Used");
        //add product to the inventory
        productService.addOrUpdateProduct(newGame);
        Product actual = productService.getProduct("0101");

        assertEquals("0101", actual.getProductId());
        assertEquals("Test Game", actual.getProductName());
        assertEquals(2, actual.getQuantity());
        assertEquals(new BigDecimal("10.00"), actual.getPrice());

        assertEquals("PhysicalGame", actual.getProductType());
        PhysicalGame actualGame = (PhysicalGame) actual;
        assertEquals("Computer", actualGame.getPlatform());
        assertEquals("Austin", actualGame.getStoreLocation());
        assertEquals("Used", actualGame.getCondition());
    }


    @Test
    @DisplayName("Delete a product from inventory")
    void deleteProductFromInventory() {
        Product game = new DigitalGame("D001", "Download Me", 1, new BigDecimal("9.99"), "1.5", "Windows");
        productService.addOrUpdateProduct(game);

        productService.removeProduct("D001");
        Product actual = productService.getProduct("D001");

        assertEquals(null, actual);
    }


    @Test
    @DisplayName("Add multiple products into inventory")
    void addMultipleProductsIntoInventory() {
        productService.addOrUpdateProduct(new Merch("M01", "T-Shirt", 5, new BigDecimal("15.00"), "Large", "Cotton", 0.5));
        productService.addOrUpdateProduct(new DigitalGame("D02", "Game B", 2, new BigDecimal("20.00"), "3.0", "Mac"));

        List<Product> actual = productService.getAllProducts();
        int expected = 2;

        assertEquals(expected, actual.size());
    }


    @Test
    @DisplayName("Find product in the inventory by ID")
    void findProductByIdSuccessfully() {
        DigitalGame expected = new DigitalGame("D123", "Cyber Quest", 3, new BigDecimal("29.99"), "Computer", "ABC123KEY");
        productService.addOrUpdateProduct(expected);

        Product actual = productService.getProduct("D123");

        assertEquals(expected.getProductId(), actual.getProductId());
        assertEquals(expected.getProductName(), actual.getProductName());
        assertEquals(expected.getQuantity(), actual.getQuantity());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getProductType(), actual.getProductType());

        DigitalGame actualGame = (DigitalGame) actual;
        assertEquals(expected.getPlatform(), actualGame.getPlatform());
        assertEquals(expected.getDownloadKey(), actualGame.getDownloadKey());
    }


    @Test
    @DisplayName("Add a product by ID and returns failure")
    void addProductByEmptyId_ReturnsFailure() {
        Product invalidProduct = new PhysicalGame("", "Game", 1, new BigDecimal("19.99"), "Switch", "LA", "New");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.addOrUpdateProduct(invalidProduct);
        });

        assertEquals("The ID cannot be null or empty", exception.getMessage());
    }



}
