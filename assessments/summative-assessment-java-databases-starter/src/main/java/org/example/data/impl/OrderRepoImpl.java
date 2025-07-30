package org.example.data.impl;

import org.example.data.OrderRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepoImpl implements OrderRepo {

    @Override
    public Order getOrderById(int id) throws RecordNotFoundException, InternalErrorException {
        throw new RecordNotFoundException();
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
