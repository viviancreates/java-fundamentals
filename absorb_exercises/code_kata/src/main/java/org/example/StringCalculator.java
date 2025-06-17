package org.example;

import java.util.regex.Pattern;

//implements actual array interface, give array of elements, can create objects but they are fixed
//need list to use the array list
import java.util.ArrayList;
//list like interface, cannot create object type list, use as reference
import java.util.List;

public class StringCalculator {
    //declare negative list
    private ArrayList<String> negatives;

    //constructor initializes the list -> gives list called negatives
    public StringCalculator() {
        negatives = new ArrayList<String>();
    }

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        /*
        } else if (!numbers.contains(",")) {
            return Integer.parseInt(numbers);
        } else {
            //3[] combination of comma and newline -> character class, match any one character inside the brakcers
            //matches a comma or matches a newline, split where you see a comma or newline
            // can add to myultiple types of string methods like .trim()
            String[] nums = numbers.split("[,\n]");
            return getSum(nums);
        }
    }
    */

        String[] nums;
        if (numbers.startsWith("//")) {
            //[0] is "//;" and [1] is "1;2"

            String[] parts = numbers.split("\n", 2);
            String delimiter = parts[0].substring(2); //extract the delimeter, //....
            nums = parts[1].split(Pattern.quote(delimiter));
        } else {
            nums = numbers.split("[,\n]");
        }


        //private int getSum(String[] nums) {
        int sum = 0;



        //go through each element callit num, array nums, add sum parse int
        for (String num : nums) {
            //converts the string to ints
            int value = Integer.parseInt(num);
            //sum += Integer.parseInt(num);
            //look at value and add to list if negative
            if(value <0){
                //store the negs
                negatives.add(num);
            }
            //add the positives together
            sum += value;
        }

        //
         //check if the list is not empty so the condition is false until there are numbers making it true
        if (!negatives.isEmpty()) {
            //throw the exception, stop the program
            //joins all the strings in the negatices list into a single string,commas separate them
            throw new IllegalArgumentException("Negatives not allowed: " + String.join(",", negatives));
        }
        //if no exceptiuin thrownm retuyrn sum
        return sum;
    }

    //4.
//[delimeter]\n[numbers...]
/*
- check if input stars with //
- take out the custome dleiimeter
- update split logic - use custom delimeter if not use regular
- remove delimeters, then remove first line //

*/
}


