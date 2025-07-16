package com.example.inventory_manager.model;

import java.math.BigDecimal;

public abstract class Product {
    private String productId;
    private String productName;
    private int quantity;
    private BigDecimal price;

    public Product (String productId, String productName, int quantity, BigDecimal price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format( "ID: %s - Name: %s - Qty: %d - Price: $%s",
                productId, productName, quantity, price);
    }

    public abstract String getProductType() {
    }
}
