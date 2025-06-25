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

    //method to display choices from array
    public static void displayChoices(String[] choices) {
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ". " + choices[i]);
        }
    }

    //method to prompt for string input
    public static String promptUserForString(String prompt) {
        Scanner console = new Scanner(System.in);
        System.out.print(prompt);
        return console.nextLine();
    }

    //method to prompt for int input
    public static int promptUserForInt(String prompt) {
        Scanner console = new Scanner(System.in);
        System.out.print(prompt);
        return Integer.parseInt(console.nextLine());
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

        //create arrays to contain addresses and sizes
        String[] addresses = {"123 Main St", "456 Main St", "789 Main St"};
        String[] sizes = {"small", "medium", "large"};

        int addressIndex = 0;
        int sizeIndex = 0;

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
            //prompt for confirmation
            isConfirmed = promptUserForString("Confirm Order y/n ").equalsIgnoreCase("y");

            //collect user info and answers
            taxExempt = promptUserForString("Are you tax-exempt? (y/n) ");

            //prompt for shipping address
            System.out.println("Choose shipping address: ");
            displayChoices(addresses);
            addressIndex = promptUserForInt("Shipping address? ");

            //prompt for shipping type
            shipping = promptUserForString("Shipping? (standard/overnight/twoday) ");

            //prompt for order quantity
            orderQuantity = promptUserForInt("Order quantity? ");

            //prompt for product size
            System.out.println("Choose product size: ");
            displayChoices(sizes);
            sizeIndex = promptUserForInt("Size? ");

            //prompt for promo code
            promoCode = promptUserForString("Promo code for free shipping? (y/n) ");
        }

        //print out user info
        System.out.println("Details: ");
        System.out.println("Tax exempt: " + taxExempt);
        System.out.println("Shipping: " + shipping);
        System.out.println("Order quantity: " + orderQuantity);
        System.out.println("Promo code: " + promoCode);
        System.out.println("Shipping address: " + addresses[addressIndex - 1]);
        System.out.println("Size: " + sizes[sizeIndex - 1]);

        //Apply tax, if necessary
        //checking to see if the order is tax exempt, use if statement
        if (taxExempt.equalsIgnoreCase("n")) {
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
        switch (shipping.toLowerCase()) {
            case "standard":
                shippingCost = standardShipping;
                if (promoCode.equalsIgnoreCase("FREE")) {
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
