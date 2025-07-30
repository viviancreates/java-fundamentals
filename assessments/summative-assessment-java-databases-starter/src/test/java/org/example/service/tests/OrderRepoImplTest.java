package org.example.service.tests;

import org.example.data.OrderRepo;
import org.example.model.Order;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.exceptions.InternalErrorException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Optional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Sql(scripts = "/sql/reset_db.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class OrderRepoImplTest {

    @Autowired
    private OrderRepo orderRepo;

    @Test
    @DisplayName("Get order by ID")
    void getOrderByIdReturnsOrder() throws Exception {
        Order order = orderRepo.getOrderById(1);

        assertNotNull(order);
        assertEquals(1, order.getOrderID());
    }

    @Test
    @DisplayName("Get order by ID and throws when not found")
    void getOrderByIdAndNotFound() {
        assertThrows(Exception.class, () -> orderRepo.getOrderById(999));
    }

    @Test
    @DisplayName("Get all orders and returns list")
    void getAllOrdersReturnsList() throws Exception {
        List<Order> orders = orderRepo.getAllOrders();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    @DisplayName("Add order successfully inserts and returns ID")
    void addOrderInsertsSuccessfully() throws Exception {
        Order newOrder = new Order();
        newOrder.setServerID(2);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setSubTotal(new BigDecimal("20.00"));
        newOrder.setTax(new BigDecimal("1.80"));
        newOrder.setTip(new BigDecimal("3.00"));
        newOrder.setTotal(new BigDecimal("24.80"));

        Order result = orderRepo.addOrder(newOrder);
        assertNotNull(result);
        assertTrue(result.getOrderID() > 0);
    }

    @Test
    @DisplayName("Delete newly added order and confirm it is removed")
    void deleteNewOrderAndConfirmRemoval() throws Exception {
        // Add a new order first
        Order newOrder = new Order();
        newOrder.setServerID(2);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setSubTotal(new BigDecimal("30.00"));
        newOrder.setTax(new BigDecimal("2.70"));
        newOrder.setTip(new BigDecimal("4.00"));
        newOrder.setTotal(new BigDecimal("36.70"));

        Order addedOrder = orderRepo.addOrder(newOrder);
        assertNotNull(addedOrder);
        int newOrderId = addedOrder.getOrderID();

        // Now delete the order
        Order deletedOrder = orderRepo.deleteOrder(newOrderId);
        assertNotNull(deletedOrder);
        assertEquals(newOrderId, deletedOrder.getOrderID());

        // Confirm it's gone by checking getOrderById throws
        assertThrows(RecordNotFoundException.class, () -> orderRepo.getOrderById(newOrderId));
    }

}
