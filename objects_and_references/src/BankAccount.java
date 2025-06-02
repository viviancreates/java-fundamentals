public class BankAccount {
    String owner;
    double balance;

    //constructor
    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void displayBalance() {
        System.out.println(owner + "Balance: " + balance);
    }
}
