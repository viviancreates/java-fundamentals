package com.example.inventory_manager.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GamePerk extends Product{
    private String perkName;
    private LocalDate expirationDate;
    private boolean isTradeable;
    private String perkDownloadCode;

    public GamePerk(String productId, String productName, int quantity, BigDecimal price, String perkName, LocalDate expirationDate, boolean isTradeable, String perkCode) {
    super(productId, productName, quantity, price);
    this.perkName = perkName;
    this.expirationDate = expirationDate;
    this.isTradeable = isTradeable;
    this.perkDownloadCode = perkDownloadCode;
    }

    public String getPerkName() {
        return perkName;
    }


    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public boolean isTradeable() {
        return isTradeable;
    }


    public String getPerkDownloadCode() {
        return perkDownloadCode;
    }


    @Override
    public String getProductType() {
        return "GamePerk";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" - Perk: %s - Expires: %s - Tradable: %b - Perk Code: %s",
                perkName, expirationDate, isTradable, perkCode
        );
    }
}
