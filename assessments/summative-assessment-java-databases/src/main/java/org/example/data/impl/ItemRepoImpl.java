package org.example.data.impl;

import org.example.data.ItemRepo;
import org.example.data.mappers.ItemMapper;
import org.example.data.mappers.ItemCategoryMapper;
import org.example.model.Item;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.List;
import org.example.model.Item;
import org.example.model.ItemCategory;

@Repository
public class ItemRepoImpl implements ItemRepo {

    @Autowired
    private JdbcTemplate jdbc;
    private ItemMapper itemMapper;
    private ItemCategoryMapper itemCategoryMapper;

    @Autowired
    public ItemRepoImpl(JdbcTemplate jdbc,
                        ItemMapper itemMapper,
                        ItemCategoryMapper itemCategoryMapper) {
        this.jdbc = jdbc;
        this.itemMapper = itemMapper;
        this.itemCategoryMapper = itemCategoryMapper;
    }

    @Override
    public Item getItemById(int id) throws RecordNotFoundException, InternalErrorException {
        String sql = "SELECT * FROM `Item` WHERE ItemID = ?";
        try {
            return jdbc.queryForObject(sql, itemMapper, id);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            throw new RecordNotFoundException();
        } catch (Exception e) {
            throw new InternalErrorException();
        }
    }

    @Override
    public List<Item> getAllAvailableItems(LocalDate today) throws InternalErrorException {
        String sql = "SELECT * FROM `Item` WHERE StartDate <= ? AND (EndDate IS NULL OR EndDate >= ?)";
        try {
            return jdbc.query(sql, itemMapper, today, today);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
    }

    @Override
    public List<Item> getItemsByCategory(LocalDate today, int itemCategoryID) throws InternalErrorException {
        String sql = "SELECT * FROM `Item` WHERE ItemCategoryID = ? AND StartDate <= ? AND (EndDate IS NULL OR EndDate >= ?)";
        try {
            return jdbc.query(sql, itemMapper, itemCategoryID, today, today);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
    }

    @Override
    public List<ItemCategory> getAllItemCategories() throws InternalErrorException {
        String sql = "SELECT * FROM `ItemCategory`";
        try {
            return jdbc.query(sql, itemCategoryMapper);
        } catch (Exception e) {
            throw new InternalErrorException();
        }
    }
}
