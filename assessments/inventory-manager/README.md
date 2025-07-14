# Inventory Manager Console Application
This is a Java built inventory management application built to practice Java OOP, file handling, and unit testing

## Structure

The application uses:
- `Product`, `PhysicalGame`, `DigitalGame`, `PhysicalMerch` -> domain models
- `ProductRepository` (interface)
- JUnit Testing -> test service logic
- CSV file handling
```
    List<Product> getAll();

    List<Product> getInStock();

    void add(Product product);

    void update(Product product);

    void delete(String productId);

    Product getById(String productId);
```

## Features (Methods)
- Add a product to Inventory
- Update product information
- Delete a product
- Retrieve all products
- Search for a product
- Save products to CSV file
- Load products from a CSV file