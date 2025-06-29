package org.example.factory;
import org.example.model.Item;

public class ItemFactory {
    public Item createItem(String name, double price) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Item name can not be left blank.");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price can not be negative");
        }

        return new Item(name, price);
    }

}
