package org.example.inventory;

import java.util.HashMap;
import org.example.model.Item;
import org.example.factory.ItemFactory;

public class Inventory {
    //create a private field called items -> stores the key and value of the actual items
    //key is the string like "shirt" and the value is the Item object(shirt with name "shirt" and price 20.0)
    private HashMap<String, Item> items;

    //THIS IS THE INVENTORY CONSTRUCTOR, so at the beginning the inventory has these productskn
    public Inventory(ItemFactory itemFactory) {
        items = new HashMap<>();

        //Items in the inventory currently
        addItem("Shirt", itemFactory.createItem("Shirt", 20.00));
        addItem("Shoes", itemFactory.createItem("Shoes", 50.00));
        addItem("Bag", itemFactory.createItem("Bag", 100.00));
        addItem("Headband", itemFactory.createItem("Headband", 15.00));
        addItem("Sweater", itemFactory.createItem("Sweater", 35.00));
    }

    //add new item to inventory
    //the keys is what the user is looking up like shirt or shoes
    //the item is the actual value, the actual objext, shirt with name and price
    public void addItem(String key, Item item) {
        //adds item to inventory hashmap
        items.put(key.toLowerCase(), item);
    }

    //get an item by the key, using its name
    //if the item exist, return the object
    public Item getItem(String key) {
        return items.get(key.toLowerCase());
    }

    //check if an item exists in the inventory
    public boolean hasItem(String key) {
        return items.containsKey(key.toLowerCase());
    }


//for (Integer key : monthMap.keySet()) {
  //      System.out.println("Month #" + key + " is " + monthMap.get(key))
    //}
// return all the keys (names) in the inventory
    public void displayInventory() {
        for (String key : items.keySet()) {
            Item item = items.get(key);
            System.out.println("Item: " + item.getName() + " | Price: $" + item.getPrice());
        }
    }
}
