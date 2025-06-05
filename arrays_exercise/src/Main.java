import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        //part 1: Basic Array Operations
        //declare and assign an array
        String[] cities = {"Chicago", "Atlanta", "Austin", "Seattle", "Dallas"};
        System.out.println(cities[0]);
        System.out.println(cities[1]);
        System.out.println(cities[2]);
        System.out.println(cities[3]);
        System.out.println(cities[4]);

        //print again before change
        System.out.println(cities[2]);
        //access and modify elements
        cities[2] = "Nueva York";
        System.out.println(cities[2]);

        //find the length of an array
        System.out.println(cities.length);

        //part 2: Iterating Over Arrays
        //print array elements using a loop
        //loop through ciity names arrays using for loop and print each city
        for  (int i = 0; i < cities.length; i++ ) {
            System.out.println("City [" + i + "] = " + cities[i]);
        }

        //use a for loop to print city in reverse order
        //wrong: for(int i = 4; i < cities.length-1; i-- ){
        //start at the last index of the array (cities.length - 1)
        //loop continues as long as index i is greater than or equal to 0
        //decrement i in each iteration to move backwards through the array
        for (int i = cities.length-1; i >= 0; i--){
            System.out.println("City [" + i + "] = " + cities[i]);
        }

        //find a specfic element
        System.out.println("Enter a city name: ");
        String name = console.nextLine();

    }
}