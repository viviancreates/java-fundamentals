import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the line item app!");

        // use scanner instance to get user name and product
        //use print rather than println to stay on same line as prompt
        System.out.print("What is your name? ");
        String name = console.nextLine();
        System.out.print("What product would you like to purchase? ");
        String product = console.nextLine();

        // get quantity
        System.out.print("How many would you like? ");
        String quantityStr = console.nextLine();
        int quantity = Integer.parseInt(quantityStr);

        // use parseDouble to get product cost
        System.out.print("What is the product cost? ");
        String costStr = console.nextLine();
        double cost = Double.parseDouble(costStr);

        // Print summary
        System.out.printf("Line Item Summary for: %s\n", name);
        System.out.println("Item: Qty\tUnit Price\tLine Total (incl. tax)");
        System.out.println("================= ===\t==========\t==========");
        System.out.printf("%s\t%d\t$%s\t\t$%s", product, quantity, cost, (quantity * cost * 1.75));
    }
}