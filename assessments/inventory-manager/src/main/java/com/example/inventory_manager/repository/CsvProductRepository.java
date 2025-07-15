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

    private void saveToFile() {
        File file = new File(filename);
        if(!file.exists()) {
            return; //again if file does not exist, start with an empty products
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != mull) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == ) {

                }
            }
        }
    }

    private void loadFromFile() {

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

    @Override
    public void add(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        //no need for getProductName.getId like below
        //inventory.put(item.getBook().isbn(), item);
        //because it is a wrapper
        //put it inthe hashmap
        products.put(product.getProductId(), product);
        saveToFile();
    }

    @Override
    public void update(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        String id = product.getProductId;

        //if the map does not contain the key id -> if true
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Product ID" + id + "can not be found.")
        }
    }

    @Override
    public void delete(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("The ID cannot be null or empty");

        }
        products.remove(id);
        saveToFile();
    }

    @Override
    punlic Products getByProductId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("The ID cannot be null or empty");
        }
        return products.get(id);
    }

}
