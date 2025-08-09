package com.example.test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private String id;
    private String merchant;
    private String category;
    private BigDecimal amount;
    private LocalDate date;
    private String description;

    public Transaction(String id, String merchant, String category, BigDecimal amount, LocalDate date, String description) {
        this.id = id;
        this.merchant = merchant;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    // Getters
    public String getId() { return id; }
    public String getMerchant() { return merchant; }
    public String getCategory() { return category; }
    public BigDecimal getAmount() { return amount; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setMerchant(String merchant) { this.merchant = merchant; }
    public void setCategory(String category) { this.category = category; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return String.format("%s: %s - $%.2f (%s) - %s",
                date, merchant, amount, category, description);
    }
}