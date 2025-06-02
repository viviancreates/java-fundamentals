public class Employee {
    String name;
    //the class variable shared by all emplyeed
    static int totalEmployees = 0;

    //cannot put static in a methid parameter
    public Employee(String name){
        this.name = name;
        totalEmployees++;
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }
}
