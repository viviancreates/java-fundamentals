package org.example.data.mappers;

import org.example.model.ItemCategory;
import org.springframework.jdbc.core.RowMapper;

public class ItemCategoryMapper {
    public static RowMapper<ItemCategory> map() {
        return (rs, rowNum) -> {
            ItemCategory category = new ItemCategory();
            category.setItemCategoryID(rs.getInt("ItemCategoryID"));
            category.setItemCategoryName(rs.getString("ItemCategoryName"));
            return category;
        };
    }
}