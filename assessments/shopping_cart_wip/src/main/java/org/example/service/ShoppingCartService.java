package org.example.service;
import org.example.model.Item;
import java.util.ArrayList;
import java.util.HashMap;


public class ShoppingCartService {
    private ArrayList<Item> items;
    //this map tracks the key and value (String itemname, int quantity)
    private HashMap<String, Integer> itemQuantity;

    //creates new empty list and hash map
    public ShoppingCartService() {
        items = new ArrayList<>();
        itemQuantity = new HashMap<>();
    }

    //REFACTOR TO USE ITEM FACTORY
    //add item method with price
    //public void addItem(String name, double price) {
    //    items.add(new Item(name, price));
    //}
    public void addItem(Item item) {
        //this is calling.add on the items list -> add this Item object to the ArrayList(Item) called items that lives inside cart
        items.add(item);

        //REFACTOR for hashmap
        //1. update addItem() toi track quantity in the quantity map
        //item is created in the factory, then use the name as a key in the map
        String name = item.getName();
        //if the item is already added to the mao, increase count by one
        if (itemQuantity.containsKey(name)) {
            int currentQuantity = itemQuantity.get(name);
            itemQuantity.put(name, currentQuantity + 1);
            //otherwise add item to map with quantity 1
        } else {
            itemQuantity.put(name, 1);
        }


    }


    //remove item method
    public Item removeItem(int index) {
        return items.remove(index);
    }

    //return items in the shopping cart
    public ArrayList<Item> getItems() {
        //not returning actual list, creates copy
        return new ArrayList<>(items);
    }

    public HashMap<String, Integer> getItemQuantity() {
        return itemQuantity;
    }

    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }


}
