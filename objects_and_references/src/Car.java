public class Car {
    //these are the instance variables
    String brand;
    String model;
    int year;

    //this is the constructor to initialize variables
    // use a constructor to create a new object, so the class is the blueprint
    // and the constructor is creating a new car each time method called
    public Car(String brand, String model, int year) {
        //using this -> takes the value from the constructor parameter, stores
        //it in the object's brand field
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    //method that prints car details
    public void displayInfo() {
        System.out.println(brand + " " + model + " (" + year + ")");
    }
}
