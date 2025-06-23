public class Main {
    public static void main(String[] args) {

        //interface called shoppable
        //if all items are media, then mayb your can interface of consume media, play media,
        //functions for shopping cart based on the interface
        //class will  - interface is for designing multiple dfifferent shopping carts

    }

    //if I have a collection of employees


    //main menu
    // we do not know ehn the user is going exit -> going through a predictable series of steps
    //unpredictable -> woould use a while loop


    // Top down and bottom up approaches to writing out the program
    //top down
    //user interface - print out he menu, get this into a loop, when the user hits 5, exit thje loop,
    //then start adding functionality bit by bit
    //add item first, write code thaty makes the add item first, add print statemtns
    //NOW you cna add items then exit

    //NEXT logical think
    //display the items


    //now we have add items, display and exit

    // next checkout

    //REMOVE from the cart is the fianl thing i would write


    //NOW WHEN WE HAVE THE FUNCTIONALITY.
    //we think abouyt what is an item
    // thenj start with abstract items


    //perishable vs decorations
    //last step is the content


    //BOTTOM
    /*
    design the items class
    extend into different types of items,
    print the items
    then next thing would be to design collection class, add items, hold items, make it displayable,
    then last thing would be main ->
            ......





    TESTING
    JUNIT TEST
    ...
    ASSUME THE THING is going to function how you want it to work,

    ex, if a shopping cart was workingm,

    //whats the simplest thing a shopping cart should be able to do
    I want it to be empty
    some way to tell me how mnay items are in it
    some way to list all the items -> need to be able to have items
    simpelst things, what would change if something is added to cart
    so now, if the shopping cart has to habe many items...
    total number of items -> test counts inmstead of starting with
    ----- text empty cart,

    FINAL thing would be removal...


    ----
    GENERICS

    writing a cart that can only hold strings limits me,
    we want a cart that can hold any type of thing
    we want a cart that holds t, which is any type of thing
    the cart does not care what type pf things we are holding, -> cart class handles generics
    - we tell the cart whay type of things we want them to hold, distinct from each other
    - carts do not need to overlap -> grocery cart, applicance, collections of carts that have different types of items but use the same class
    -

class Cart<T> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public int getItemCount() {
        return items.size();
    }
}

@Test
    void testCartWithStrings() {
        Cart<String> cart = new Cart<>();
        cart.add("Apple");
        cart.add("Banana");
        cart.add("Orange");

        assertEquals(3, cart.getItemCount(), "Cart should contain 3 strings");
    }

    @Test
    //item is class -> collection of classes
    void testCartWithItems() {
        Cart<Item> cart = new Cart<>();
        cart.add(new Item("Apple"));
        cart.add(new Item("Banana"));
        cart.add(new Item("Orange"));

        assertEquals(3, cart.getItemCount(), "Cart should contain 3 Item objects");
    }





    ------
    class Item {
   public Item(String name) {
   }
}

class Student {
   public Student(String sid, int grade) {
  }
}

Cart<Student> nextYearsStudents = new Cart<>();



    -------
    class Cart<T> {
        private List<T> items = new ArrayList<>();

        public void add(T item) {
            items.add(item);
        }

        public int getItemCount() {
            return items.size();
        }
    }
    class Cart {
        private List<Student> items = new ArrayList<>();

        public void add(Student item) {
            items.add(item);
        }

        public int getItemCount() {
            return items.size();
        }
    }
    ------------------------------------
    class Cart {
        private List<String> items = new ArrayList<>();

        public void add(String item) {
            items.add(item);
        }

        public int getItemCount() {
            return items.size();
        }
    }

    Cart myCart = new Cart<Student>()
    myCart = new Cart<Vegetables>()

}