package com.example.inventory_manager.model;

import java.math.BigDecimal;

public class DigitalGame extends Product {
    private String platform;
    private String downloadKey;

    public DigitalGame(String productId, String productName, int quantity, BigDecimal price, String platform, String downloadKey) {
        super(productId, productName, quantity, price);
        this.platform = platform;
        this.downloadKey = downloadKey;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDownloadKey() {
        return downloadKey;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" - Platform: %s - DownloadKey: %s",
                        platform, downloadKey);
    }

    @Override
    public String getProductType() {
        return "DigitalGame";
    }
}
