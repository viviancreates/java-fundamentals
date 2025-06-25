package org.example;

import org.example.service.ShoppingCartService;
import org.example.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.example.factory.ItemFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCartServiceTest {

    /*
    EXAMPLE
    private StringCalculator calc;
    @BeforeEach
    void setUp() {
        calc = new StringCalculator();
    }
    @Test
    @DisplayName("Empty string returns zero")
    void emptyStringReturnsZero() {
        int actual = calc.add("");
        int expected = 0;

        assertEquals(expected, actual);
    }
    */

    private ShoppingCartService scs;
    private ItemFactory itemFactory;

    @BeforeEach
    void setUp() {
        scs = new ShoppingCartService();
    }

    @BeforeEach
    void setUp() {
        itemFactory = new ItemFactory();
    }


    @Test
    @DisplayName("The shopping cart is initialized")
    public void shoppingCartInitializedAsEmpty() {
        //ShoppingCartService scs = new ShoppingCartService();
        Assertions.assertTrue(scs.getItems().isEmpty());
    }

    @Test
    @DisplayName("The shopping cart can add an item")
    public void canAddItem() {
        //create the SC service
       // ShoppingCartService scs = new ShoppingCartService();
        //create a party

        //THESE TWO LINES ARE WRONG BC ITS LOOKING AT ADDITEM FROM SC AND THAT TAKES IN THE FIELDS, DOES NOT MAGICALLY ADD the i
        //Item i = new Item("Shirt", 50.0 );
        //sc.addItem(i);

        //REFACTOR - here the item will be created by the factory, them we just pass the item
        //scs.addItem("Shirt", 50.0);
        Item shirt = itemFactory.createItem("Shirt", 50.0);
        scs.addItem(shirt);

        //this adds how many items
        assertEquals(1, scs.getItems().size());

        //are there now two items
        scs.addItem("Item2", 20.0);
        assertEquals(2, scs.getItems().size());
    }


    @Test
    @DisplayName("Shopping cart is adding correct items (returns the list)")
    public void itemAddedIsCorrect() {
       // ShoppingCartService scs = new ShoppingCartService();
        scs.addItem("Shirt", 50.0);
        scs.addItem("Headband", 20.0);

        Item secondItem = scs.getItems().get(1);
        assertEquals("Headband", secondItem.getName());
        assertEquals(20, secondItem.getPrice());
    }

    /*
    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }
    */

    @Test
    @DisplayName("Shopping cart returns total")
    public void getTotalCostFromCart() {
        scs.addItem("Shirt", 50.0);
        scs.addItem("Headband", 20.0);

        double actual = scs.getTotal();
        double expected = 70.0;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Shopping cart can remove item")
    public void removeItemFromCart() {
        scs.addItem("Shirt", 50.0);
        scs.addItem("Headband", 20.0);
        scs.addItem("Socks", 10.0);
        scs.addItem("Shoes", 100.0);



        assertEquals(4, scs.getItems().size());

        Item gone = scs.removeItem(1);
        assertEquals("Headband", gone.getName());
        assertEquals(3, scs.getItems().size());
    }

}
