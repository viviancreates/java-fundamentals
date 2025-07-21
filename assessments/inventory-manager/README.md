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

## Learnings
**CSV Output Comparison**

Current Format

The image below shows the current output of the CSV file, where each row is printed differently depending on the product type:

![CSV example showing mismatched rows](images/csvnotesimg.png)

This structure:
- Has different numbers of columns per row
- Is hard to import into tools like Excel or MySQL
- Makes parsing and maintenance error-prone
- Has no consistent header, so column meanings are unclear


What I’m Changing It To -> I'm switching to a unified CSV format where every row has the same columns, even if some values are left blank
