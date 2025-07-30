package org.example.service.tests;

import org.example.data.ItemRepo;
import org.example.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

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
}
