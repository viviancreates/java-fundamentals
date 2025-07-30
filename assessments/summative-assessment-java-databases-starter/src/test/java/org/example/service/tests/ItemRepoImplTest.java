package org.example.service.tests;

import org.example.data.ItemRepo;
import org.example.model.Item;
import org.example.model.ItemCategory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
@Sql(scripts = "/sql/reset_db.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ItemRepoImplTest {

    @Autowired
    private ItemRepo itemRepo;

    @Test
    void testGetItemById_ReturnsItem() throws Exception {
        Item item = itemRepo.getItemById(1);
        assertNotNull(item);
        assertEquals(1, item.getItemID());
    }

    @Test
    void testGetAllItemCategories_ReturnsList() throws Exception {
        List<ItemCategory> categories = itemRepo.getAllItemCategories();
        assertNotNull(categories);
        assertFalse(categories.isEmpty(), "Category list should not be empty");

        // Optionally check first category's structure
        ItemCategory first = categories.get(0);
        assertNotNull(first.getItemCategoryID());
        assertNotNull(first.getItemCategoryName());
    }
}
