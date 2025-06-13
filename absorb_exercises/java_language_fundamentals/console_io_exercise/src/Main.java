 import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        //use scanner instance to get user name and product details
        System.out.print("What is you name? ");
        String name = console.nextLine();

        System.out.print("What product would you like to purchase? ");
        String product = console.nextLine();

        System.out.print("How many would you like? ");
        String quantityStr = console.nextLine();
        int quantity = Integer.parseInt(quantityStr);

        System.out.print("What is the unit price? " );
        String costStr = console.nextLine();
        double cost = Double.parseDouble(costStr);

        //calculate total price
        double subtotal = quantity * cost;
        double tax = subtotal * .07;
        double grandTotal = subtotal + tax;

        //print Order summary
        //----
        System.out.println("\nOrder Summary");
        System.out.println("-------------------------------");
        //Item: dsfdsf
        //Qty: 1
        //Unit price: $2.12
        //---
        System.out.printf("Item:            %s\n", product);
        System.out.printf("Quantity:        %s\n", quantity);
        System.out.printf("Unit Price:      $%s\n", cost);
        System.out.println("-------------------------------");
        //Subtotal
        //Tax
        //grand total
        //----
        System.out.printf("Subtotal:        $%s\n", subtotal);
        System.out.printf("Tax (7%%):        $%s\n", tax);
        System.out.printf("Grand Total:     $%s\n", grandTotal);
        System.out.println("-------------------------------");
        //Thank you for your order, name!
        System.out.printf("Thank you for your order, %s!\n", name);
    }
}