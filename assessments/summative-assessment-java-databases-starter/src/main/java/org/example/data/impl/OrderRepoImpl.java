package org.example.data.impl;

import org.example.data.OrderRepo;
import org.example.model.Order;
import org.example.data.mappers.OrderMapper;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepoImpl implements OrderRepo {
    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Order getOrderById(int id) throws RecordNotFoundException, InternalErrorException {
        String sql = "SELECT * FROM `Order` WHERE OrderID = ?";
        try {
            return jdbc.queryForObject(sql, OrderMapper.map(), id);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            throw new RecordNotFoundException();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Order> getAllOrders() throws InternalErrorException, RecordNotFoundException {
        return new ArrayList<>();
    }

    @Override
    public Order addOrder(Order order) throws InternalErrorException {
        return order;
    }

    @Override
    public void updateOrder(Order order) throws InternalErrorException {

    }

    @Override
    public Order deleteOrder(int id) throws InternalErrorException {
        return null;
    }
}
