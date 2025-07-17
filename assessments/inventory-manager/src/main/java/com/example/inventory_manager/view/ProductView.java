package com.example.inventory_manager.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.inventory_manager.command.Command;
import com.example.inventory_manager.command.AddOrUpdateCommand;
import com.example.inventory_manager.command.GetAllProductsCommand;
import com.example.inventory_manager.command.GetProductByIdCommand;
import com.example.inventory_manager.command.RemoveProductCommand;
import com.example.inventory_manager.repository.CSVProductRepository;
import com.example.inventory_manager.repository.ProductRepository;
import com.example.inventory_manager.service.ProductService;
import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductView {
    private final ProductService productService;
    private final ProductIO productIO;

    @Autowired
    public ProductView(ProductService productService, ProductIO productIO) {
        this.productService = productService;
        this.productIO = productIO;
    }

    Command addOrUpdateProductCommand = new AddOrUpdateProductCommand(productService, productIO);
    Command getAllProductsCommand = new GetAllProductsCommand(productService, productIO);
    Command removeProductCommand = new RemoveProductCommand(productService, productIO);
    Command getProductByIdCommand = new GetProductByIdCommand(productService, productIO);

    public void run() {
        productIO.displayWelcome();
        boolean running = true;
        while (running) {
            int choice = productIO.displayMenuAndGetChoice();

            switch (choice) {
                case 1:
                    addOrUpdateProductCommand.execute();

                    break;
                case 2:
                    removeProductCommand.execute();
                    break;
                case 3:
                    getProductByIdCommand.execute();
                    break;
                case 4:
                    getAllProductsCommand.execute();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    productIO.displayError("Invalid choice. Please try again.");
                    break;
            }
        }
        productIO.displayGoodbye();
    }
}
