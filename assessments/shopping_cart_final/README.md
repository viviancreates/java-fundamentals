# Shopping Cart Assessment
This is a simple shopping cart program built to learn Java OOP. 

## Structure

The application uses:
- `ArrayList<Item>` --> store contents
- `ItemFactory` --> create Item objects
- `HashMap<String, Integer>` --> keep track of name and item object, specific Item quantities
- JUnit Testing --> create tests for the service (backend functionality)
- Interface command --> learn command pattern use

## Features (Methods)
- Add an item to the shopping cart
- Remove an item
- View all items in teh cart
- Checkout of the cart with total cost

## To Do
**Priority**
- Refactor Tests

**Maybe**
- Refactor app --> some repeating happening

- Finish refactor for maps
    - how to add items in one single line, right now if I add 2 shirts  and 1 shoe
      ```
      (1)shoe for $ 2.99
      (2)shirt for $ 5.99
      (2)shirt for $ 5.99
      AND
      Your final shopping cart has the items:
      1. shoe for $ 2.99
      2. shirt for $ 5.99
      3. shirt for $ 5.99
      ```
      
- Allow user to custom add item?