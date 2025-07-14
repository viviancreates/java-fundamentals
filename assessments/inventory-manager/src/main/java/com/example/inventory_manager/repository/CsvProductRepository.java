package com.example.inventory_manager.repository;

import com.example.inventory_manager.model.Product;
import com.example.inventory_manager.model.PhysicalGame;
import com.example.inventory_manager.model.DigitalGame;
import com.example.inventory_manager.model.PhysicalMerch;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CsvProductRepository implements ProductRepository {

    private final Map<String, Product> products = new HashMap<>();
    @Value("${inventory.csv.filepath:data/inventory.csv}")
    private String filename;

    @PostConstruct
    public void init() {
        loadFromFile();
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> getInStock() {
        return products.values().stream()
                .filter(product -> item.getQuantity() > 0)
                .collect(Collectors.toList());
    }


}
