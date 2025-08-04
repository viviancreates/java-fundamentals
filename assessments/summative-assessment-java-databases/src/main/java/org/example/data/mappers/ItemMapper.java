package org.example.data.mappers;

import org.example.model.Item;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.math.BigDecimal;

@Component
public class ItemMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
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
