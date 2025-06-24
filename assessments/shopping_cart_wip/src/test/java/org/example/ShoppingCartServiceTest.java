package org.example;

import org.example.service.ShoppingCartService;
import org.example.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCartServiceTest {
    @Test
    public void shoppingCartInitializedAsEmpty() {
        ShoppingCartService sc = new ShoppingCartService();
        Assertions.assertTrue(sc.getItems().isEmpty());
    }

    @Test
    public void canAddItem() {
        //create the SC service
        ShoppingCartService sc = new ShoppingCartService();
        //create a party

        //THESE TWO LINES ARE WRONG BC ITS LOOKING AT ADDITEM FROM SC AND THAT TAKES IN THE FIELDS, DOES NOT MAGICALLY ADD the i
        //Item i = new Item("Shirt", 50.0 );
        //sc.addItem(i);
        sc.addItem("Shirt", 50.0);

        //this adds how many items
        assertEquals(1, sc.getItems().size());

        //are there now two items
        sc.addItem("Item2", 20.0);
        assertEquals(2, sc.getItems().size());
    }


    @Test
    public void itemAddedIsCorrect() {
        ShoppingCartService sc = new ShoppingCartService();
        sc.addItem("Shirt", 50.0);
        sc.addItem("Headband", 20.0);

        Item secondItem = sc.getItems().get(1);
        assertEquals("Headband", secondItem.getName());
        assertEquals(20, secondItem.getPrice());
    }



}
