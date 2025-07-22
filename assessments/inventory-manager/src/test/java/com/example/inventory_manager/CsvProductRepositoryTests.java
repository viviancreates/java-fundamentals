package com.example.inventory_manager;

import com.example.inventory_manager.service.ProductService;
import com.example.inventory_manager.model.*;
import com.example.inventory_manager.repository.CsvProductRepository;
import com.example.inventory_manager.repository.ProductRepository;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CsvProductRepositoryTests {
    private ProductRepository productRepository;
    private ProductService productService;

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

        productRepository = new CsvProductRepository();
        productService = new ProductService(productRepository);
    }

    @AfterEach
    void tearDown() {
        // Clean up test file after each test
        File file = new File("data/test.csv");
        if (file.exists()) {
            file.delete();
        }
    }



    @Test
    @DisplayName("ProductService Initializes as Empty")
    public void productServiceInitializedEmpty() {
        List<Product> products = productService.getAllProducts();
        assertTrue(products.isEmpty());
    }

    @Test
    @DisplayName("Add a product to the CSV")
    public void addProductToCsvSuccessfully {

        
    }

    /*
    @Test
    @DisplayName("Delete a product from CSV")

    @Test
    @DisplayName("Update a product in CSV")

    @Test
    @DisplayName("Fail to load from CSV file")

     */
    }