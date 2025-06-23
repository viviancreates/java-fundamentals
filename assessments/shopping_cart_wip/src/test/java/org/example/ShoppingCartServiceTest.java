package org.example;

import org.example.service.ShoppingCartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCartServiceTest {
    @Test
    public void shoppingCartInitializedAsEmpty() {
        ShoppingCartService sc = new ShoppingCartService();
        Assertions.assertTrue(sc.getItems().isEmpty());
    }


}
