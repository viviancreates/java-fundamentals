package org.example;

import org.example.service.ShoppingCartService;
import org.example.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


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

    @BeforeEach
    void setUp() {
        scs= new ShoppingCartService();
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
        scs.addItem("Shirt", 50.0);

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



}
