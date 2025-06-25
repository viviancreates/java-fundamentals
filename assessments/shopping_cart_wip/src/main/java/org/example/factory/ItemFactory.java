package org.example.factory;

import org.example.model.Item;

//A factory will make things cleaner when creating an object...
/*
- example get rid of creating items all over the place, will be much easier to refactor

so.. instead of new ITem(name, price)
I can just put itemFactory.createItem(name, price) -> when we add the hashmap, it should be easier to refactor
 */
public class ItemFactory {
    public Item createItem(String name, double price) {
        // validations to make sure ppl dont leave blank etc
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Item name can not be left blank.");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price can not be negative");
        }

        //calling the item class constructor
        return new Item(name, price);
    }

}
