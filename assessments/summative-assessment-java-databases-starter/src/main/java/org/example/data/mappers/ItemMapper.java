package org.example.data.mappers;

import org.example.model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.math.BigDecimal;

public class ItemMapper {
    public static RowMapper<Item> map() {
        return (rs, rowNum) -> {
            Item item = new Item();

            item.setItemID(rs.getInt("ItemID"));
            item.setItemCategoryID(rs.getInt("ItemCategoryID"));
            item.setItemName(rs.getString("ItemName"));
            item.setItemDescription(rs.getString("ItemDescription"));
            item.setStartDate(rs.getDate("StartDate").toLocalDate());

            if (rs.getDate("EndDate") != null) {
                item.setEndDate(rs.getDate("EndDate").toLocalDate());
            }

            item.setUnitPrice(rs.getBigDecimal("UnitPrice"));

            return item;
        };
    }
}