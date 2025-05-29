//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int productId = 1;
        int productCategory = 2;
        double productCost = 2.56;
        double productPrice = 4.99;
        int productQuantity = 78;

        //total cost
        double totalCost = productCost * productQuantity;
        System.out.println("$" + totalCost);

        //profit margin
        double profitMargin = productPrice - productCost;
        System.out.println("$" + profitMargin);

        //total profit
        double totalProfit = profitMargin * productQuantity;
        System.out.println("$" + totalProfit);
    }
}