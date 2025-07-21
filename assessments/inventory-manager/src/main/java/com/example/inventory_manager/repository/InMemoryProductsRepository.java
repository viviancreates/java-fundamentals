package com.example.inventory_manager.repository;

import com.example.inventory_manager.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class InMemoryProductsRepository implements ProductRepository {

    private final Map<String, Product> products = new HashMap<>();

    public InMemoryProductsRepository() {
        initializeSampleData();
    }

    private void initializeSampleData() {
        addPhysicalGame("PG001", "The Last of Us", 5, "59.99", "PlayStation", "Austin", "New");
        addPhysicalGame("PG002", "Halo", 3, "50.00", "Xbox", "Chicago", "Used");

        addDigitalGame("DG001", "Stardew Valley", 10, "14.99", "PC", "16851651651");
        addDigitalGame("DG002", "Game2", 7, "24.99", "Switch", "516516564");

        addMerch("M001", "Zelda Hoodie", 3, "39.99", "Apparel", "L", 16.0);
        addMerch("M002", "Mario Plushie", 8, "19.99", "Toy", "One Size", 8.0);

        addGamePerk("GP001", "XP Boost", 100, "4.99", "XP", LocalDate.of(2025, 12, 31), true, "perk222ddd");
        addGamePerk("GP002", "Skin", 50, "2.99", "Armor", LocalDate.of(2026, 1, 1), false, "perk102bbb");
    }

    private void addPhysicalGame(String id, String name, int qty, String price, String platform, String location, String condition) {
        products.put(id, new PhysicalGame(id, name, qty, new BigDecimal(price), platform, location, condition));
    }

    private void addDigitalGame(String id, String name, int qty, String price, String platform, String downloadKey) {
        products.put(id, new DigitalGame(id, name, qty, new BigDecimal(price), platform, downloadKey));
    }

    private void addMerch(String id, String name, int qty, String price, String type, String size, double weight) {
        products.put(id, new Merch(id, name, qty, new BigDecimal(price), type, size, weight));
    }

    private void addGamePerk(String id, String name, int qty, String price, String perkName, LocalDate expiration, boolean isTradeable, String code) {
        products.put(id, new GamePerk(id, name, qty, new BigDecimal(price), perkName, expiration, isTradeable, code));
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> getInStock() {
        return products.values().stream()
                .filter(p -> p.getQuantity() > 0)
                .toList();
    }

    @Override
    public void add(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        products.put(product.getProductId(), product);
    }

    @Override
    public void update(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        String id = product.getProductId();
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Product ID " + id + " not found.");
        }

        products.put(id, product);
    }

    @Override
    public void delete(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }
        products.remove(productId);
    }

    @Override
    public Product getById(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }
        return products.get(productId);
    }
}
