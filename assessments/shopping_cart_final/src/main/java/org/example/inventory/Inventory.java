package org.example.inventory;
import java.util.HashMap;
import org.example.model.Item;
import org.example.factory.ItemFactory;

public class Inventory {
    private HashMap<String, Item> items;

    public Inventory(ItemFactory itemFactory) {
        items = new HashMap<>();

        addItem("Shirt", itemFactory.createItem("Shirt", 20.00));
        addItem("Shoes", itemFactory.createItem("Shoes", 50.00));
        addItem("Bag", itemFactory.createItem("Bag", 100.00));
        addItem("Headband", itemFactory.createItem("Headband", 15.00));
        addItem("Sweater", itemFactory.createItem("Sweater", 35.00));
    }

    public void addItem(String key, Item item) {
        //adds item to inventory hashmap
        items.put(key.toLowerCase(), item);
    }

    public Item getItem(String key) {
        return items.get(key.toLowerCase());
    }

    public boolean hasItem(String key) {
        return items.containsKey(key.toLowerCase());
    }

    public void displayInventory() {
        for (String key : items.keySet()) {
            Item item = items.get(key);
            System.out.println("Item: " + item.getName() + " | Price: $" + item.getPrice());
        }
    }
}
