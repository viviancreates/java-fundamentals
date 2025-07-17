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
import java.time.LocalDate;

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
       try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
           for (Product product : products.values()) {
               String type = product.getProductType();

               //can probably take out base product
               if (type.equals("PhysicalGame")) {
                   PhysicalGame physicalGame = (PhysicalGame) product;
                   writer.printf("%s,$s,%d,%.2f,%s,%s,%s,%s%n",
                           physicalGame.getProductId(),
                           physicalGame.getProductName(),
                           physicalGame.getQuantity(),
                           physicalGame.getPrice(),
                           type,
                           physicalGame.getPlatform(),
                           physicalGame.getStoreLocation(),
                           physicalGame.getCondition()
                   );
               } else if (type.equals("DigitalGame")) {
                   DigitalGame digitalGame = (DigitalGame) product;
                   writer.printf("%s,%s,%d,%.2f,%s,%s,%s%n",
                           digitalGame.getProductId(),
                           digitalGame.getProductName(),

                           digitalGame.getQuantity(),
                           digitalGame.getPrice(),
                           type,
                           digitalGame.getPlatform(),
                           digitalGame.getDownloadKey()
                   );

               } else if (type.equals("Merch")) {
                   Merch merch = (Merch) product;
                   writer.printf("%s,%s,%d,%.2f,%s,%s,%s,%.2f%n",
                           merch.getProductId(),
                           merch.getProductName(),

                           merch.getQuantity(),
                           merch.getPrice(),
                           type,
                           merch.getMerchType(),
                           merch.getSize(),
                           merch.getWeightInOz()
                   );
               } else if (type.equals("GamePerk")) {
                   GamePerk gamePerk = (GamePerk) product;
                   writer.printf("%s,%s,%d,%.2f,%s,%s,%s,%b,%s%n",
                           gamePerk.getProductId(),
                           gamePerk.getProductName(),

                           gamePerk.getQuantity(),
                           gamePerk.getPrice(),
                           type,
                           gamePerk.getPerkName(),
                           gamePerk.getExpirationDate(),
                           gamePerk.isTradeable(),
                           gamePerk.getPerkDownloadCode()
                   );
               }
           }
       } catch (IOException e) {
           throw new RuntimeException("Error writing to file: " + filename, e);
       }
    }

    private void loadFromFile() {
        //1. check if the file exists
        File file = new File(filename);
        if(!file.exists()) {
            return; //again if file does not exist, start with an empty products
        }

        products.clear();

        //open the file with buffered reader
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                //type tells me what subclass to build
                String type = parts[4].trim();
                String productId = null;
                Product product = null;

                if (type.equals("PhysicalGame")) {
                    String productName = parts[0].trim();
                    productId = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());
                    BigDecimal price = new BigDecimal(parts[3].trim());
                    String platform = parts[5].trim();
                    String storeLocation = parts[6].trim();
                    String condition = parts[7].trim();

                    product = new PhysicalGame(productId, productName, quantity, price, platform, storeLocation, condition);
                } else if (type.equals("DigitalGame")) {
                    String productName = parts[0].trim();
                    productId = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());
                    BigDecimal price = new BigDecimal(parts[3].trim());
                    String platform = parts[5].trim();
                    String downloadKey = parts[6].trim();

                    product = new DigitalGame(productId, productName, quantity, price, platform, downloadKey);
                } else if (type.equals("Merch")) {
                    String productName = parts[0].trim();
                    productId = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());
                    BigDecimal price = new BigDecimal(parts[3].trim());
                    String merchType = parts[5].trim();
                    String size = parts[6].trim();
                    double weightInOz = Double.parseDouble(parts[7].trim());

                    product = new Merch(productId, productName, quantity, price, merchType, size, weightInOz);
                } else if (type.equals("GamePerk")) {
                    String productName = parts[0].trim();
                    productId = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());
                    BigDecimal price = new BigDecimal(parts[3].trim());
                    String perkName = parts[5].trim();
                    LocalDate expirationDate = LocalDate.parse(parts[6].trim());
                    boolean isTradeable = Boolean.parseBoolean(parts[7].trim());
                    String perkDownloadCode = parts[8].trim();

                    product = new GamePerk(productId, productName, quantity, price, perkName, expirationDate, isTradeable, perkDownloadCode);
                }
                if (product != null) {
                    products.put(productId, product);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + filename, e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error parsing number from file: " + filename, e);
        }
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> getInStock() {
        return products.values().stream()
                .filter(product -> product.getQuantity() > 0)
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
        String id = product.getProductId();

        //if the map does not contain the key id -> if true
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Product ID" + id + "can not be found.");
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
    public Product getById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("The ID cannot be null or empty");
        }
        return products.get(id);
    }

}
