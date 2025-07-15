package com.example.inventory_manager.model;

import java.math.BigDecimal;

public class PhysicalGame extends Product {
    private String platform;
    private String storeLocation;
    private String condition;

    public PhysicalGame(String productId, String productName, int quantity, BigDecimal price, String platform, String storeLocation, String condition) {
        super(productId, productName, quantity, price);
        this.platform = platform;
        this.storeLocation = storeLocation;
        this.condition = condition;
    }

    public String getPlatform() {
        return platform;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" - Platform: %s - Store Location: %s - Condition: %s",
                        platform, storeLocation, condition);
    }

    @Override
    public String getProductType() {
        return "PhysicalGame";
    }
}
