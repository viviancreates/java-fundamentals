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

3. Once user input is taken, what happens with each choice 


    //if 1. rent a locker
        //if rent a locker, give pin
    
    //if 2. access a locker
        //if access a locker, take pin

    //if 3. release a locker
        // if release a locker, reset pin

    //any other key to exit
    
4. efresfewsf