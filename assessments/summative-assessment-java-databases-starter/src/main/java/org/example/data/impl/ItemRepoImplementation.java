package org.example.data.impl;

import org.example.data.ItemRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Item;
import org.example.model.ItemCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ItemRepoImplementation implements ItemRepo {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepoImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Item getItemById(int id) throws RecordNotFoundException, InternalErrorException {
        return null;
    }

    @Override
    public List<Item> getAllAvailableItems(LocalDate today) throws InternalErrorException {
        return List.of();
    }

    @Override
    public List<Item> getItemsByCategory(LocalDate today, int itemCategoryID) throws InternalErrorException {
        return List.of();
    }

    @Override
    public List<ItemCategory> getAllItemCategories() throws InternalErrorException {
        String sql = "SELECT * FROM itemcategory";

        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> null);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
    }

//    private ItemCategory mapItemCategory(ResultSet rs, int rowNum) throws SQLException {
//        ItemCategory category = new ItemCategory();
//        category.setItemCategoryId(rs.getInt("ItemCategoryID"));
//        category.setName(rs.getString("ItemCategoryName"));
//        return category;
//    }
}
