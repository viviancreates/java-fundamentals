public class ObjectPractice {
    public static void main(String[] args) {
        //p1
        Car car1 = new Car("Toyota", "Corolla", 2020);
        Car car2 = new Car("Ford", "Mustang", 2022);
        car1.displayInfo();
        car2.displayInfo();

        //p2
        Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", true);
        book1.displayStatus();
        System.out.println("Borrowing the book...");
        book1.borrowBook();
        book1.displayStatus();

        //p3
        //twi variable, same account object, just using the same info would not make them the same object
        BankAccount acc1 = new BankAccount("Vivian", 1000.0);
        BankAccount acc2 = acc1;

        acc1.displayBalance();

        //balance after deposit
        System.out.println("Depositing $500 to acc2");
        acc2.deposit(500);
        acc2.displayBalance();

        //p4
        new Employee("Vivs");
        new Employee("Andrea");
        new Employee("Davila");
        System.out.println("Total Employees: " + Employee.getTotalEmployees());

    }
}
