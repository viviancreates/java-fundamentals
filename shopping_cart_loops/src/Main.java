import java.util.Scanner;

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
        Scanner console = new Scanner(System.in);

        //add string variables
        String businessName = "Vivian's Business";
        String businessContactInfo = "123-456-7891";
        String productDescription = "Nice things";

        //print the variables
        System.out.println("Business name: " + businessName);
        System.out.println("Business contact info: " + businessContactInfo);
        System.out.println("Product description: " + productDescription);
        System.out.println();

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

        //product info
        int productId = 1;
        int productCategory = 2;
        double productCost = 2.56;
        double productPrice = 4.99;
        int productQuantity = 78;

        double taxRate = .07;
        double hundredDollarDiscount = .05;
        double fiveHundredDollarDiscount = .10;

        double standardShipping = 2.00;
        double overnightShipping = 10.00;
        double twoDayShipping = 5.00;

        //total cost
        double totalCost = productCost * productQuantity;
        System.out.println("$" + totalCost);

        //profit margin
        double profitMargin = productPrice - productCost;
        System.out.println("$" + profitMargin);

        //total profit
        double totalProfit = profitMargin * productQuantity;
        System.out.println("$" + totalProfit);

        //variables declared outside of the loop so they can be accessed after the loop exists
        //keeps them in scope after the code exits
        boolean isConfirmed = false;

        String taxExempt = "";
        String shipping = "";
        String promoCode = "";
        int orderQuantity = 0;

        while (!isConfirmed) {
            System.out.println("Confirm Order y/n");
            isConfirmed = "y".equals(console.nextLine());

            //collect user info and answers
            System.out.print("Are you tax-exempt? (y/n) ");
            taxExempt = console.nextLine();

            System.out.print("Shipping? (standard/overnight/twoday) ");
            shipping = console.nextLine();

            System.out.print("Order quantity? ");
            String quantityStr = console.nextLine();
            orderQuantity = Integer.parseInt(quantityStr);

            System.out.print("Promo code for free shipping? (y/n) ");
            promoCode = console.nextLine();

        }


        //print out user info
        System.out.println("Details: ");
        System.out.println("Tax exempt: " + taxExempt);
        System.out.println("Shipping: "+ shipping);
        System.out.println("Order quantity: " + orderQuantity);
        System.out.println("Promo code: " + promoCode);

        //Apply tax, if necessary
        //checking to see if the order is tax exempt, use if statement
        if (taxExempt.equals("n")) {
            totalCost = totalCost + (totalCost * taxRate);
        }

        System.out.println("Total w/tax: " + totalCost);

        //Apply discount
        //use if else statement
        if (totalCost >= 500) {
            totalCost = totalCost - (totalCost * fiveHundredDollarDiscount);
        } else if (totalCost >= 100) {
            totalCost = totalCost - (totalCost * hundredDollarDiscount);
        }

        System.out.println("Total after discount: " + totalCost);

        //Switch statement to determine shipping cost
        double shippingCost = 0.00;
        switch (shipping) {
            case "standard":
                shippingCost = standardShipping;
                if (promoCode.equals("FREE")) {
                    shippingCost = 0;
                }
                break;
            case "overnight":
                shippingCost = overnightShipping;
                break;
            case "twoday":
                shippingCost = twoDayShipping;
                break;
            default:
                //bad shipping type
                System.out.println("Invalid shipping type");
        }

        totalCost += shippingCost;
        System.out.println("Shipping Cost: " + shippingCost);
        System.out.println("Final Total " + totalCost);
    }


}