package com.example.inventory_manager.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.example.inventory_manager.model.Product;
import com.example.inventory_manager.model.PhysicalGame;
import com.example.inventory_manager.model.DigitalGame;
import com.example.inventory_manager.model.Merch;
import com.example.inventory_manager.model.GamePerk;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

public class CsvProductRepository implements ProductRepository {

    private final Map<String, Product> products = new HashMap<>();
    @Value("${inventory.csv.filepath:data/inventory.csv}")
    private String filename;

    @PostConstruct
    public void init() {
        loadFromFile();
    }

    private void saveToFile() {
       try (PrintWriter writer = new PrintWriter(new Filewriter(filename))) {
           for (Product product : products.values()) {
               String type = product.getProductType();

               if (type.equals("PhysicalGame")) {
                   PhysicalGame physicalGame = (PhysicalGame) product;
                   writer.printf("%s,$s,%d,%.2f,%s,%s,%s,%s%n",
                           physicalGame.getProductName(),
                           physicalGame.getProductId(),
                           physicalGame.getQuantity(),
                           physicalGame.getPrice(),
                           type,
                           physicalGame.getPlatform(),
                           physicalGame.getStoreLocation(),
                           physicaGame.getCondition()
                   );
               } else if (type.equas("DigitalGame")) {
                   DigitalGame digitalGame = (DigitalGame) product;
                   writer.printf("%s,%s,%d,%.2f,%s,%s,%s%n",
                           digitalGame.getProductName(),
                           digitalGame.getProductId(),
                           digitalGame.getQuantity(),
                           digitalGame.getPrice(),
                           type,
                           digitalGame.getPlatform(),
                           digitalGame.getDowloadKey()
                   );

               } else if (type.equals("Merch")) {
                   Merch merch = (Merch) product;
                   writer.printf("%s,%s,%d,%.2f,%s,%s,%s,%.2f%n",
                           merch.getProductName(),
                           merch.getProductId(),
                           merch.getQuantity(),
                           merch.getPrice(),
                           type,
                           merch.getMerchType(),
                           merch.getSize(),
                           merch.getWeightInOz()
                   );
               } else if (type.equals("GamePerk")) {
                   GamePerk gamePerk = (GamePerk) product;
                   writer.printf("%s,%s,%d,%.2f,%s,%s,%s,%.2f%n",
                           gamePerk.getProductName(),
                           gamePerk.getProductId(),
                           gamePerk.getQuantity(),
                           gamePerk.getPrice(),
                           type,
                           gamePerk.getPerkName(),
                           gamePerk.getExpirationDate(),
                           gamePerk.isTradeable(),
                           gamePerk.getPerkDownload()
                   );
               }
           }
       }
    }

    private void loadFromFile() {
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
