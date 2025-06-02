public class Main {
    enum OrderStatus {
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED
    }

    enum ShippingStatus {
        STANDARD,
        TWO_DAY,
        OVERNIGHT
    }

    public static void main(String[] args) {
        //add string variables
        String businessName = "Vivian's Business";
        String businessContactInfo = "123-456-7891";
        String productDescription = "Nice things";

        //print the variables
        System.out.println("Business name: " + businessName);
        System.out.println("Business contact info: " + businessContactInfo);
        System.out.println("Product description: " + productDescription);
        System.out.println();

        //product info
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

        //Shopping Cart APP with Enums
        System.out.println(ShippingStatus.STANDARD);
        System.out.println(ShippingStatus.TWO_DAY);
        System.out.println(ShippingStatus.OVERNIGHT);
        OrderStatus orderStatus = OrderStatus.PROCESSING;
        System.out.println("Order Status: " + orderStatus);

        System.out.println(OrderStatus.PENDING);
        System.out.println(OrderStatus.PROCESSING);
        System.out.println(OrderStatus.SHIPPED);
        System.out.println(OrderStatus.DELIVERED);
        ShippingStatus shipStatus = ShippingStatus.TWO_DAY;
        System.out.println("Ship Status: " + shipStatus);
    }
}