# thoughts and notes

**Create four main classes:**
  - Locker (with number and pin properties)
  - LockerService (handling rent, access, and release operations)
  - IO class (for user interaction)
  - Main (for application workflow)
---
- The Result Pattern -> A reusable "Result" class was recommended for error handling: see below

```
class Result {
    final boolean success;
    final String message;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
```
---
# tips from class
- Separation of Concerns
  - Key design principles emphasized:
  - Only use Scanner and System.out.println in one class (IO)
  - Keep business logic separate from user interaction
  - Make the LockerService configurable for the number of lockers
  - Use null to represent a locker that hasn't been assigned a PIN

---
# what am i doing (talking to self)
- set up -> making sure user io works on main

1. print menu

2. take user input - gave user menu, take input
- i tried to add a display choice loop, unneccesaary, took it out
- ... i lied, it does need to be inside a loop for it to remember the data -> see step 8

3. Once user input is taken, what happens with each choice 


    //if 1. rent a locker
        //if rent a locker, give pin
    
    //if 2. access a locker
        //if access a locker, take pin

    //if 3. release a locker
        // if release a locker, reset pin

    //any other key to exit

- create this if else statement in main vs lcoker service
- create in main bc it what the user decides
- add the methods to locker service bc
    
4. work on locker - bc how can we create the methods without knowing thee data
- each locker rented need to generate a pin 
- is the locker rented
- decided between 2 constructors below
```
//constructor with no arguments
     public Locker() {
        isRented = false;
     }
```

```
//constructor with several arguments
//we want to have this constructor because we are creatijgnseveral lockers
    public Locker (String lockerNumber, String pin, boolean isRented){
        this.lockerNumber = lockerNumber;
        this.isRented = isRented;
        this.pin = pin;
}
```
- if went with the constructor with arguments, the data would be hardcoded?
- right???

- lockers class represents one locker, with its own state
  - is the locker rented or available?
  - what is the locker pin
  - what is the locker number
- give the locker private fields (private variables)
- when we first create the lcoker object, it starts out available, with no pin or number
- bc the variables are private, we give controlledf access with getters and setters

5. add getters(returns value of a field, return locker #) and setters (set the value of a field, ex change the pin) for data(variables)

6. lockerservice -> hold methods
- manage all the lockers
- call methods on individual lockers
- does not change pin, or availability
- only asks locker (blackbox from each other)
  - to rent the locker, asks for the pin and number
  - to access locker, asks for the pin
  - to release the locker, n/a just set it back to available
- service methods finished

7. go back to locker
- encapsulation continued

8. flow in main
  - added the while true loop to remember the objects(arrays?)

9. make sure all scanner info and user io is in io class
- biggest issue was lockerservice -> want to have lockerservice only receive parameters and return results, not to to chat with the user(io), that should only be io class
  - ****** UserIO io = new UserIO(); add to locker service, delete utiil, replace all print statements (similar to main)

# misc
-locker just holds the data (REMEMBER architecture does 3 things ->tasks, main, data)
- question -> how do you decide what to work on first, flow seems easiest, then the methods, but how to check... 
  - right now i am just making sure they connect, does that seem right????

- better to do the opposite and build back to front

- fields are just class variables and always have getters and setters ->private, can it be public?

# reminders

- add validation
- try catch at the end
- add leading zeros to pin