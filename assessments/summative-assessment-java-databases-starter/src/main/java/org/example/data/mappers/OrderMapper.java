package org.example.data.mappers;

import org.example.model.Order;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;

public class OrderMapper {
    public static RowMapper<Order> map() {
        return (rs, rowNum) -> {
            Order order = new Order();
            order.setOrderID(rs.getInt("OrderID"));
            order.setServerID(rs.getInt("ServerID"));
            order.setOrderDate(rs.getTimestamp("OrderDate").toLocalDateTime());
            order.setSubTotal(rs.getBigDecimal("SubTotal"));
            order.setTax(rs.getBigDecimal("Tax"));
            order.setTip(rs.getBigDecimal("Tip"));
            order.setTotal(rs.getBigDecimal("Total"));
            return order;
        };
    }
}