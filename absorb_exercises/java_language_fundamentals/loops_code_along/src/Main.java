import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //control structures are statements or structures in Java that control the flow of the code
        System.out.println("Welcome to the loops demo!");
        Scanner console = new Scanner(System.in);
        String input = "";

        //1. while
        //scenario - process orders 1 to 5
        System.out.println("Process with while loop:");
            //inside of the loop, create a variable and initialize
            int orders = 1;
            //execute block of code repeatedly until the condition is no longer true (while orders are less
            //than or equal to 5)
            while(orders <= 5){
                System.out.println("Processing Order #" + orders);

                orders++;
            }

        //2. do-while
        System.out.println("Process with do-while loop:");
            orders = 5;
            do{
                System.out.println("Processing Order #" + orders);
                orders--;
            }while(orders > 0);

        //3. while evaluating user input - favorite hobbies
        //common use of using while loops, ask user fave hobbies until they enter ! to quit
        System.out.println("Accept input until '!");
        System.out.println("Enter your favorite hobbies (! to quit):");
            while(!input.equals("!")){
                System.out.println("Favorite hobby?");
                input = console.nextLine();
                System.out.println(input + " entered. \n");
            }

        //4. for
        //for loops have three parts -> initialized starting variable, a condition, a step
        System.out.println("for loop - print 1 to 10");
            for (int i=1; i<=10; i++){
                System.out.println("i = " + i);
            }
        //the first part delcares and initializes variable to one
        //semicolons separate each section
        //the step executes after block of code but before condition statement
        //this example increments variable by one each time the loop executes

        System.out.println("for loop - print 10 to 1");
            for (int i =10; i>0; i--){
                System.out.println("i = " + i);
            }

        //common use of for loops is iterating over an array or string of characters
        //for loop(initialize; condition; step){
        //      code code code
        //}
        System.out.println("Process a string in a for loop");
            String message = "Hello! How are you?";
            for(int i=0; i < message.length(); i++){
                System.out.println(message.charAt(i));
            }

        //break -> allows us to exit loop immediately, regardless of condition
        //while evaluating user input
        //if the input quals anchovies or ! exit the loop, otherwise print mmm message
        System.out.println("Accept input until '!' entered");
            input = "";
            while(!input.equals("!")) {
                System.out.println("Favorite topping? ");
                input = console.nextLine();
                if(input.equals("anchovies") || input.equals("!")){
                    break;
                }
                System.out.println("mmm.." + input + "\n");
            }

        //continue -> allows us to skip to start of next iteration
        //evaluate user input for valid numbers (>0)
        //using the continue keyword in this example means you do not need an else, bc the keyword skips to the next iteration
        System.out.println("Enter your favorite whole numbers! ");
        input = "";
        while (!input.equals("0")){
            System.out.println("Favorite number?");
            input = console.nextLine();
            int nbr = Integer.parseInt(input);
            if(nbr<0){
                System.out.println("Oops! Negative #s are not allowed");
                continue;
            }
            System.out.println("You've entered " + nbr + ".\n");
        }

        //infinite loop -> loop with a condition that it will always be true
        //a while(true)  is infinite bc the condition is never evaluated to be false
        //also if the body of loop does not modify the variable the condition is checking
        boolean isValid = false;
            System.out.println("Even number check");
            while(!isValid){
                System.out.println("Enter an even number: ");
                input = console.nextLine();
                int nbr = Integer.parseInt(input);
                if(nbr %2 == 0) {
                    System.out.println("Yep, that's an even number!");
                    //enter isValid = true; to make sure there is no infinite loop -> thas a problem
                    isValid = true;
                }else{
                    System.out.printf("Nope, that's an odd number.");
                }
            }

    }
}