package com.example.inventory_manager.command;
import com.example.inventory_manager.model.*;
import com.example.inventory_manager.service.ProductService;
import com.example.inventory_manager.view.ProductIO;
import java.time.LocalDate;
import java.math.BigDecimal;

public class GetProductByIdCommand implements Command {
    private final ProductService productService;
    private final ProductIO productIO;

    public GetProductByIdCommand(ProductService productService, ProductIO productIO) {
        this.productService = productService;
        this.productIO = productIO;
    }

    @Override
    public void execute() {
        printHeader();

        String productId = productIO.getStringInput("Enter the product ID to search: ");
        if (productId.isEmpty()) {
            productIO.displayError("Product ID cannot be empty.");
            return;
        }

        Product product = productService.getProduct(productId);

        if (product != null) {
            productIO.displayMessage(product.toString());
        } else {
            productIO.displayError("No product found with ID: " + productId);
        }
    }

    private void printHeader() {
        productIO.displayMessage("=========================");
        productIO.displayMessage("    View Product By ID   ");
        productIO.displayMessage("=========================");
    }
}
