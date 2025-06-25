import java.util.ArrayList;

//handle multiple lockers
public class LockerManager {
    private ArrayList<Locker> lockers;

    //LockerManager(): Constructor that initializes the list of lockers
    public LockerManager() {
        lockers = new ArrayList<>();
    }

    //void addLocker(String lockerId): Adds a new locker to the list
    public void addLocker(String lockerId) {
       //first create a new locker using the locker id
        //add locker object into list of locker objects
        Locker newLocker = new Locker(lockerId);
        lockers.add(newLocker);
    }

    //Locker getLocker(String lockerId): Retrieves a locker by its ID
    public Locker getLocker(String lockerId) {
        for (Locker locker : lockers) {
            if (locker.getLockerId().equals(lockerId)) {
                return locker;
            }
        }
        return null; // not found
    }

    //void removeLocker(String lockerId): Removes a locker from the list by its ID
    public void removeLocker(String lockerId) {
        Locker toRemove = getLocker(lockerId);
        if (toRemove != null) {
            lockers.remove(toRemove);
            System.out.println("Locker " + lockerId + " removed.");
        } else {
            System.out.println("Locker not found.");
        }
    }

    //void displayAllLockers(): Displays information for all lockers
    public void displayAllLockers() {
        for (Locker locker : lockers) {
            System.out.println(locker);
        }
    }
}

