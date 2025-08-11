package com.example.capstone_scratch.model;

import java.math.BigDecimal;

public class TransactionRequest {
    private String fromAddress;
    private String toAddress;
    private String privateKey;
    private BigDecimal amount;

    // Empty constructor req for Spring
    public TransactionRequest() {}

    public TransactionRequest(String fromAddress, String toAddress, String privateKey, BigDecimal amount) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.privateKey = privateKey;
        this.amount = amount;
    }

    // Getters and setters
    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "fromAddress='" + fromAddress + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", amount=" + amount +
                '}';
    }
}