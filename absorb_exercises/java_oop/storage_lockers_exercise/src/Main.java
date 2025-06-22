public class Main {
    public static void main(String[] args) {
        LockerManager manager = new LockerManager();

        manager.addLocker("1");
        manager.addLocker("2");

        Locker lockerA = manager.getLocker("1");
        if (lockerA != null) {
            lockerA.storeItem("Something small.");
        }

        manager.displayAllLockers();

        lockerA.removeItem();

        manager.removeLocker("2");

        manager.displayAllLockers();
    }
}