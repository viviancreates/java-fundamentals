package com.example.inventory_manager.config;

import com.example.inventory_manager.repository.CsvProductRepository;
import com.example.inventory_manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductsConfig {
    @Value("${inventory.repository.type:csv}")
    private String repositoryType;
    private String filepath;

    @Bean
    public ProductRepository productRepository() {
        switch (repositoryType.toLowerCase()) {
            case "csv":
                return new CsvProductRepository(filepath);
            default:
                throw new IllegalArgumentException(
                        "Invalid repository type: " + repositoryType +
                                ". Supported types are: 'csv'");
        }
    }

}
