package org.example.service;
import org.example.model.Item;
import java.util.ArrayList;
import java.util.HashMap;


public class ShoppingCartService {
    private ArrayList<Item> items;
    private HashMap<String, Integer> itemQuantity;

    public ShoppingCartService() {
        items = new ArrayList<>();
        itemQuantity = new HashMap<>();
    }


    public void addItem(Item item) {
        items.add(item);
        String name = item.getName();
        if (itemQuantity.containsKey(name)) {
            int currentQuantity = itemQuantity.get(name);
            itemQuantity.put(name, currentQuantity + 1);
        } else {
            itemQuantity.put(name, 1);
        }
    }

    public Item removeItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if(item.getName().equalsIgnoreCase(name)) {
                items.remove(i);
                itemQuantity.remove(name);
                return item;
            }
        }
    throw new IndexOutOfBoundsException("Item not found.");
    }

    public ArrayList<Item> getItems() {
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
