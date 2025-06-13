public class Locker {
//holds state and if rented, give a pin, access, check the pin, if release, ?
    //each locker rented need to generate a pin - random 4 digit string
    //is the locker rented

    //notes from class
    //state is stored info and kept around
    //terminal utilities - besides the scanner, there is no state
    //if there is no state, then it can be static -> only once copy
    //we need more than one locker and they need to keep there data aroud
    private String lockerNumber;
    private String pin;
    private boolean isRented;

    //constructor is a set up for the new object
    //constructor with no arguments
    //locker is free
    //user has no pin or locker number
    public Locker() {
        isRented = false;
        this.pin = "";
        this.lockerNumber = "";
    }

    //getters and setters
    // workbook example
    /*
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isCheckedOut() {
        return checkedOutBy != null;
    }
*/

    public String getPin(){
        return pin;
    }

    private void setPin(String pin) {
        this.pin = pin;
    }

    public boolean isRented(){
        return isRented;
    }

    public String getLockerNumber(){
        return lockerNumber;
    }

    private void setLockerNumber(String lockerNumber){
        this.lockerNumber = lockerNumber;
    }

    //if the locker is rented
    public void rentLocker(String pin, String lockerNumber) {
        this.isRented = true;
        this.pin = pin;
        this.lockerNumber = lockerNumber;
    }
    //to release the locker
    public void releaseLocker() {
        this.isRented = false;
        this.pin = "";
        this.lockerNumber = "";
    }
    //to check the pin
    public boolean checkPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }
}