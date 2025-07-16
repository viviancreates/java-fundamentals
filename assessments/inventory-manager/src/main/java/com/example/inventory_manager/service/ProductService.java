package com.example.inventory_manager.service;

public class ProductService {
    // depends on the interface 
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addOrUpdateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        String id = product.getProductId();
        Product existingProduct = productRepository.getByProductId(id);

        if (existing != null) {
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
        return productRepository.getByProductId(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

}
