# Chatting about the inventory manager

1. Create the Spring application and load into intelliJ
2. Created my pojos - use of inheritance 



# To Do
- [x] Domain models
- [ ] Create Repository
    - [ ] In Memory
    - [ ] CSV Repo
- [ ] Service
- [ ] IO
- [ ] Main
- [ ] Unit Tests
- [ ] File handling works (problems in airport)

- [ ] Abstract class
- [ ]Refactor 
  - try to use an enum for game condition
  - maybe add more product types with dates, ints, etc
# Learning
How service stays flexible
Dependency Injection:

ProductService service = new ProductService(new CsvProductRepository());
vs

ProductService service = new ProductService(new InMemoryProductRepository());

- Same service, different repo — so your tests stay fast and your production logic stays persistent.




# Questions