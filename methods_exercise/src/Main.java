import java.util.Scanner;

public class Main {

    //1. define and call a simple method
    public static void printWelcomeMessage(){
        System.out.println("Welcome to the Java Methods Exercise!");
    }

    //2. calculate the sum of two numbers
    //the method sum takes in two parameter with type and name
    public static int sum(int a, int b){
        int sum = a + b;
        return sum;
    }

    //3. convert celsius to fahrenheit
    public static double convertToFahrenheit(double celsius){
        double fahrenheit = (celsius * (9.0/5.0)) + 32.0;
        return fahrenheit;
    }

    //4. check if a number is even or odd
    public static boolean isEven(int number){
        if (number % 2 == 0){
            return true;
        }else{
            return false;
        }
    }

    //main method
    public static void main(String[] args){
        //1. call the simple method inside main
        printWelcomeMessage();

        //2.
        int sumResult = sum(10, 5);
        System.out.println(sumResult);

        //3.
        double fahrenheitResult = convertToFahrenheit(55.0);
        System.out.println(fahrenheitResult);

        //4.
        boolean checkNumber = isEven(10);
        System.out.println(checkNumber);







    }
}