package com.example.inventory_manager.service;

import com.example.inventory_manager.repository.CsvProductRepository;
import com.example.inventory_manager.repository.ProductRepository;
import com.example.inventory_manager.model.Product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    // depends on the interface
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addOrUpdateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        String id = product.getProductId();
        Product existingProduct = productRepository.getById(id);

        if (existingProduct != null) {
            // updat -> overwrite quantity & price
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setPrice(product.getPrice());
            productRepository.update(existingProduct);
        } else {
            // add a new product
            productRepository.add(product);
        }
    }

    public void removeProduct(String id) {
        productRepository.delete(id);
    }

    public Product getProduct(String id) {
        return productRepository.getById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

}
