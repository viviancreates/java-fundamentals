package com.example.inventory_manager.model;

import java.math.BigDecimal;

public class PhysicalMerch extends Product {
    private String merchType;
    private String size;
    private double weightInOz;

    public PhysicalMerch (String productId, String productName, int quantity, BigDecimal price, String merchType, String size, double weightInOz) {
        super(productId, productName, quantity, price);
        this.merchType = merchType;
        this.size = size;
        this.weightInOz = weightInOz;
    }

    public String getMerchType() {
        return merchType;
    }

    public String getSize() {
        return size;
    }

    public double getWeightInOz() {
        return weightInOz;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" - MerchType: %s - Size: %s - Weight: %.2f oz",
                merchType, size, weightInOz);
    }

    @Override
    public String getProductType() {
        return "PhysicalMerch";
    }
}
