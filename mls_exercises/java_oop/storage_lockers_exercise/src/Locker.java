//represents individual storage lockers
//start thinking fields, getters, setters
public class Locker {
    private String lockerId;
    private boolean isOccupied;
    private String contents;

    //Locker(String lockerId): Constructor that initializes the locker with a given ID, sets isOccupied to false, and contents to an empty string
    //if I were to use this.isOccupied for example, it would update the fields to false, the actual locker state
    //bc we need to create new local variables for isoccupied and for contents, do not use this keyword
    //CREATE A LOCAL VARIABLE
    public Locker(String lockerId) {
        this.lockerId = lockerId;
        boolean isOccupied = false;
        String contents = "";
    }

    public String getLockerId(){
        return lockerId;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public String contents() {
        return contents;
    }


    //void storeItem(String item): Stores an item in the locker, sets isOccupied to true and updates the contents
    public void storeItem(String item) {
        //steps
        //1.check if the locker is already occupied, if it is maybe return a message
        //2. if the lcoker is not occupied, set contents to items being stored, set is occupied to true
        if (isOccupied) {
            System.out.println("Locker is occupied!");
        } else {
            contents = item;
            isOccupied = true;
            System.out.println("Locker is available. Please add items.");
        }
    }

    //void removeItem(): Empties the locker, sets isOccupied to false, and clears the contents
    public void removeItem() {
        //check to see if there is an item in contents
        //if so, remove the item
        if (contents.isEmpty()) {
            System.out.println("There are not items in the locker.");
        } else {
            System.out.println("Removing items...");
            contents = "";
            isOccupied = false;
        }
    }

    //String toString(): Returns a string representation of the locker, including its ID, occupancy status, and contents
    public String toString() {
        return "Locker Id: " + lockerId + "\nOccupied: " + isOccupied + "\nContents: " + contents;
    }
}
