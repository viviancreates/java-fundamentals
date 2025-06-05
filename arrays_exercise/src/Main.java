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

        //find a specific element
        System.out.println("Enter a city name: ");
        String name = console.nextLine();
        //check if the city is in the array
        //easier to assume the city is not int the list until I find it
        boolean inArray = false;
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equalsIgnoreCase(name)) {
                inArray = true;
                break;
            }
        }

        if (inArray) {
            System.out.println("City found");
        }else{
            System.out.println("City not found!");
        }

        //part3: Numeric Arrays and Calculations
        //sum of numbers in an array
        int[] scores = {100, 90, 80, 70, 60};
        int totalScore = 0;
        //totalScore = scores[0] + scores[1] + scores[2] + scores[3] + scores[4];
        //System.out.println(totalScore);
        for (int i = 0; i < scores.length; i++ ){
            totalScore += scores[i];
            System.out.println(totalScore);
        }
        System.out.println(totalScore);

        //find the maximum and minimum
        //starting with the first element...
        int max = scores[0];
        int min = scores[0];

        //...loop through each element and compare to previous
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        System.out.println("Max#: " + max);
        System.out.println("Min#: " + min);

        //find the average score
        int average = (totalScore / scores.length);
        System.out.println("Average Score: " + average);

        //part 4: advanced challenges
        //count occurances of a value
    }
}