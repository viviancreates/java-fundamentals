package com.example.inventory_manager.repository;

import com.example.inventory_manager.model.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> getAll();

    List<Product> getInStock();

    void add(Product product);

    void update(Product product);

    void delete(String productId);

    Product getById(String productId);
}
