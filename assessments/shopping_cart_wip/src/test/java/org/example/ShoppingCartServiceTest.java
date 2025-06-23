package org.example;

import org.example.service.ShoppingCartService;
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
        Item i = new Item("Shirt", 50.0 );
        sc.addItem(i);

        assertEquals(50.0, sc.getItems().size());
        //assertEquals(p, wl.getList().get(0));

    }

}
