package com.example.inventory_manager;

import com.example.inventory_manger.model.*;
import com.example.inventory_manager.repository.*;
import com.example.inventory_manager.service.*;

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
}
