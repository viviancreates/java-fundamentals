package com.example.inventory_manager.command;

import com.example.inventory_manager.model.*;
import com.example.inventory_manager.service.ProductService;
import com.example.inventory_manager.view.ProductIO;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

public class GetAllProductsCommand implements Command {
    private final ProductService productService;
    private final ProductIO productIO;

    public GetAllProductsCommand(ProductService productService, ProductIO productIO) {
        this.productService = productService;
        this.productIO = productIO;
    }

    @Override
    public void execute() {
        printHeader();

        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            productIO.displayMessage("The inventory is currently empty.");
        } else {
            for (Product product : products) {
                productIO.displayMessage(product.toString());
            }
        }
    }

    private void printHeader() {
        productIO.displayMessage("=============================");
        productIO.displayMessage("     All Inventory Items     ");
        productIO.displayMessage("=============================");
    }

}
