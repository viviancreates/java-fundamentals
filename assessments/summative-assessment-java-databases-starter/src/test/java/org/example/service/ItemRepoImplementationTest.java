package org.example.data;

import org.example.data.exceptions.InternalErrorException;
import org.example.model.ItemCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItemRepoImplementationTest {

    @Autowired
    ItemRepo itemRepo;

    @Test
    void testGetAllItemCategories() {
        try {
            List<ItemCategory> categories = itemRepo.getAllItemCategories();
            assertNotNull(categories);
            assertFalse(categories.isEmpty());
        } catch (InternalErrorException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}

