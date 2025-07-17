package com.example.inventory_manager.view;

import com.example.inventory_manager.repository.CSVProductRepository;
import com.example.inventory_manager.repository.ProductRepository;
import com.example.inventory_manager.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    private final ProductService productService;
    private final ProductIO productIO;

    public ProductView(ProductService productService) {
        this.productService = productService;
        this.profuctIO = productIO;
    }

    boolean running = true;

    public void run() {


        while (running) {


            switch (choice) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    addOrUpdateProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    running
                    break;
                default:
                    productIO.displayError("Invalid choice. Please try again.");
            }
        }
    }
}
