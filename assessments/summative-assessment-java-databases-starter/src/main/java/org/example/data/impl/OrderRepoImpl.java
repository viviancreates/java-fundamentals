package org.example.data.impl;

import org.example.data.OrderRepo;
import org.example.model.Order;
import org.example.data.mappers.OrderMapper;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepoImpl implements OrderRepo {
    @Autowired
    private JdbcTemplate jdbc;
    private OrderMapper orderMapper;

    public OrderRepoImpl(JdbcTemplate jdbc, OrderMapper orderMapper) {
        this.jdbc = jdbc;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order getOrderById(int id) throws RecordNotFoundException, InternalErrorException {
        String sql = """
            SELECT o.orderID, o.ServerID, OrderDate, SubTotal, Tax, Tip, Total, FirstName, LastName
            FROM `Order` o
            JOIN `Server` s ON o.ServerID = s.ServerID
            WHERE o.orderID = ?
        """;

        try {
            return jdbc.queryForObject(sql, orderMapper, id);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            throw new RecordNotFoundException();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Order> getAllOrders() throws InternalErrorException, RecordNotFoundException {
        String sql = """
            SELECT o.orderID, o.ServerID, OrderDate, SubTotal, Tax, Tip, Total, FirstName, LastName, HireDate, TermDate 
            FROM `order` o
            INNER JOIN `Server` s ON o.ServerID = s.ServerID       
        """;
        try {
            return jdbc.query(sql, orderMapper);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Order addOrder(Order order) throws InternalErrorException {
        String sql = "INSERT INTO `Order` (ServerID, OrderDate, SubTotal, Tax, Tip, Total) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbc.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, order.getServerID());
                ps.setObject(2, order.getOrderDate());
                ps.setBigDecimal(3, order.getSubTotal());
                ps.setBigDecimal(4, order.getTax());
                ps.setBigDecimal(5, order.getTip());
                ps.setBigDecimal(6, order.getTotal());
                return ps;
            }, keyHolder);

            order.setOrderID(keyHolder.getKey().intValue());
            return order;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public void updateOrder(Order order) throws InternalErrorException {
        String sql = "UPDATE `Order` SET ServerID = ?, OrderDate = ?, SubTotal = ?, Tax = ?, Tip = ?, Total = ? WHERE OrderID = ?";

        try {
            int rowsAffected = jdbc.update(sql,
                    order.getServerID(),
                    order.getOrderDate(),
                    order.getSubTotal(),
                    order.getTax(),
                    order.getTip(),
                    order.getTotal(),
                    order.getOrderID());

            if (rowsAffected == 0) {
                throw new InternalErrorException(new Exception("Order not found."));
            }
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Order deleteOrder(int id) throws InternalErrorException {
        String sql = """
            SELECT o.OrderID, o.ServerID, OrderDate, SubTotal, Tax, Tip, Total,
               s.FirstName, s.LastName
            FROM `Order` o
            JOIN `Server` s ON o.ServerID = s.ServerID
            WHERE o.OrderID = ?
        """;

        String deleteOrderItemsSql = "DELETE FROM OrderItem WHERE OrderID = ?";
        String deleteOrderSql = "DELETE FROM `Order` WHERE OrderID = ?";

        try {
            Order order = jdbc.queryForObject(sql, orderMapper, id);

            jdbc.update(deleteOrderItemsSql, id);

            int rowsAffected = jdbc.update(deleteOrderSql, id);

            if (rowsAffected == 0) {
                throw new InternalErrorException(new Exception("Delete failed."));
            }

            return order;

        } catch (EmptyResultDataAccessException e) {
            throw new InternalErrorException();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
}
