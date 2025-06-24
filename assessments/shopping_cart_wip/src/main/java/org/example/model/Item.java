package org.example.model;

//THIS IS A POJO BTW... plain old java object, holds data
public class Item {
    private String name;
    private double price;

    public Item (String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPrice(double price) {
        this.price = price;
    }
}
