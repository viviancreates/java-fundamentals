package org.example.service;
import org.example.model.Item;
import java.util.ArrayList;


public class ShoppingCartService {
    private ArrayList<Item> items;

    //creates new empty list
    public ShoppingCartService() {
        items = new ArrayList<>();
    }

    //add item method with price
    public void addItem(String name, double price) {
        items.add(new Item(name, price));
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

    public double getTotal() {
        doublt total = 0;
        for (Item item : items) {
            total += item.price;
        }
        return total;
    }


}
