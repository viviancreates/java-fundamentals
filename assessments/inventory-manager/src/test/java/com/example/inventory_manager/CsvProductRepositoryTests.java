package com.example.inventory_manager;

import com.example.inventory_manager.model.*;
import com.example.inventory_manager.repository.CsvProductRepository;
import com.example.inventory_manager.repository.ProductRepository;
import org.junit.jupiter.api.*;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CsvProductRepositoryTests {
    private static final String csvFilePath = "test.csv";
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new CsvProductRepository(csvFilePath);
    }

    @AfterEach
    void tearDown() {
        // Clean up test file after each test
        File file = new File(csvFilePath);
        if (file.exists()) {
            file.delete();
        }
    }


    /*
    @Test
    @DisplayName("Add a product from CSV")

    @Test
    @DisplayName("Delete a product from CSV")

    @Test
    @DisplayName("Update a product in CSV")

    @Test
    @DisplayName("Fail to load from CSV file")

     */
    }