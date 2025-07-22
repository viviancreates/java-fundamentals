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
           writer.println("ProductID,ProductName,Quantity,Price,Type,Platform,StoreLocation,Condition,DownloadKey,MerchType,Size,WeightInOz,PerkName,ExpirationDate,IsTradeable,PerkDownloadCode");
           for (Product product : products.values()) {
               String type = product.getProductType();

               String productId = product.getProductId();
               String productName = product.getProductName();
               int quantity = product.getQuantity();
               BigDecimal price = product.getPrice();

               String platform = "";
               String storeLocation = "";
               String condition = "";
               String downloadKey = "";
               String merchType = "";
               String size = "";
               String weightInOz = "";
               String perkName = "";
               String expirationDate = "";
               String isTradeable = "";
               String perkDownloadCode = "";

               if (type.equals("PhysicalGame")) {
                   PhysicalGame physicalGame = (PhysicalGame) product;
                   platform = physicalGame.getPlatform();
                   storeLocation = physicalGame.getStoreLocation();
                   condition = physicalGame.getCondition();
               } else if (type.equals("DigitalGame")) {
                   DigitalGame digitalGame = (DigitalGame) product;
                   platform = digitalGame.getPlatform();
                   downloadKey = digitalGame.getDownloadKey();
               } else if (type.equals("PhysicalMerch")) {
                   Merch merch = (Merch) product;
                   merchType = merch.getMerchType();
                   size = merch.getSize();
                   weightInOz = String.format("%.2f", merch.getWeightInOz());
               } else if (type.equals("GamePerk")) {
                   GamePerk gamePerk = (GamePerk) product;
                   perkName = gamePerk.getPerkName();
                   expirationDate = gamePerk.getExpirationDate().toString();
                   isTradeable = Boolean.toString(gamePerk.isTradeable());
                   perkDownloadCode = gamePerk.getPerkDownloadCode();
               }

               // Print everything in one unified row
               writer.printf("%s,%s,%d,%.2f,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                       productId, productName, quantity, price, type,
                       platform, storeLocation, condition,
                       downloadKey, merchType, size, weightInOz,
                       perkName, expirationDate, isTradeable, perkDownloadCode
               );

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
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                if (isFirstLine) {
                    isFirstLine = false; // Skip header
                    continue;
                }

                String[] parts = line.split(",", -1); // keep empty fields as empty strings
                String type = parts[4].trim();
                Product product = null;
                loadBaseProductFields base = getLoadBaseProductFields(parts);

                if (type.equals("PhysicalGame")) {
                    product = loadPhysicalGame(parts, base);
                } else if (type.equals("DigitalGame")) {
                    product = loadDigitalGame(parts, base);
                } else if (type.equals("PhysicalMerch")) {
                    product = loadPhysicalMerch(parts, base);
                } else if (type.equals("GamePerk")) {
                    product = loadGamePerk(parts, base);
                }
                if (product != null) {
                    products.put(product.getProductId(), product);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + filename, e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error parsing number from file: " + filename, e);
        }
    }

    private static Product loadGamePerk(String[] parts, loadBaseProductFields base) {
        String perkName = parts[12].trim();
        LocalDate expirationDate = LocalDate.parse(parts[13].trim());
        boolean isTradeable = Boolean.parseBoolean(parts[14].trim());
        String perkDownloadCode = parts[15].trim();
        return new GamePerk(base.productId(), base.productName(), base.quantity(), base.price(), perkName, expirationDate, isTradeable, perkDownloadCode);
    }

    private static Product loadPhysicalMerch(String[] parts, loadBaseProductFields base) {
        String merchType = parts[9].trim();
        String size = parts[10].trim();
        double weightInOz = Double.parseDouble(parts[11].trim());
        return new Merch(base.productId(), base.productName(), base.quantity(), base.price(), merchType, size, weightInOz);
    }

    private static Product loadDigitalGame(String[] parts, loadBaseProductFields base) {
        String platform = parts[5].trim();
        String downloadKey = parts[8].trim();
        return new DigitalGame(base.productId(), base.productName(), base.quantity(), base.price(), platform, downloadKey);
    }

    private static Product loadPhysicalGame(String[] parts, loadBaseProductFields base) {
        String platform = parts[5].trim();
        String storeLocation = parts[6].trim();
        String condition = parts[7].trim();
        return new PhysicalGame(base.productId(), base.productName(), base.quantity(), base.price(), platform, storeLocation, condition);
    }

    private static loadBaseProductFields getLoadBaseProductFields(String[] parts) {
        String productId = parts[0].trim();
        String productName = parts[1].trim();
        int quantity = Integer.parseInt(parts[2].trim());
        BigDecimal price = new BigDecimal(parts[3].trim());
        return new loadBaseProductFields(productId, productName, quantity, price);
    }

    private record loadBaseProductFields(String productId, String productName, int quantity, BigDecimal price) {
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
