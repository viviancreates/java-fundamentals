package com.example;

import com.example.model.Book;
import com.example.service.ShelfService;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.assertEquals;

public class ShelfServiceTest {

    @Test
    public void canCreateShelf() {
        ShelfService service = new ShelfService();

        assertEquals(0, service.getBooks().size());
    }

}
