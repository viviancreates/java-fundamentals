package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    private StringCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new StringCalculator();
    }

    //1
    @Test
    @DisplayName("Empty string returns zero")
    void emptyStringReturnsZero() {
        int actual = calc.add("");
        int expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Single number returns number")
    void oneNumberReturnsNumber() {
        int actual = calc.add("5");
        int expected = 5;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Two numbers returns sum")
    void twoNumbersReturnsSum() {
        int actual = calc.add("5,3");
        int expected = 8;

        assertEquals(expected, actual);
    }

    //2
    @Test
    @DisplayName("Return sum of multiple numbers")
    void multipleNumbersReturnsSum() {
        int actual = calc.add("5,3,2,1");
        int expected = 11;

        assertEquals(expected, actual);
    }

    //3
    @Test
    @DisplayName("Return sum with newlines")
    void newlinesReturnsSum() {
        int actual = calc.add("1\n2,3");
        int expected = 6;

        assertEquals(expected, actual);
    }

    //4
    @Test
    @DisplayName("Support different delimiters ")
    void supportDifferentDelimiters() {
        int actual = calc.add("//.\n1.2");
        int expected = 3;

        assertEquals(expected, actual);
    }

    //5
    @Test
    @DisplayName("Throws exception negatives not allowed")
    void negativeNumbersThrowsException() {
        //class refers to the type IAE, not a new instance
        // -> is a lambda expression that allows a way ....?
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.add("-1,2,3,4");
        });

        //expected result with a single negative number, actual result
        assertEquals("Negatives not allowed: -1", exception.getMessage());


        exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.add("1,-2,3,-4");
        });

        //expected vs actual
        assertEquals("Negatives not allowed: -1,-2,-4", exception.getMessage());

    }

    @Test
    @DisplayName("Numbers greater than 1000 ignores")
        void numbersGreater1000Ignore(){
        int actual = calc;
        int expected = ;

        assertEquals(expected, actual);

    }



}




    /*void testExceptionIsThrown() {
        throwException(); // Method that is expected to throw IllegalArgumentException
    }
    private void throwException() {
        throw new IllegalArgumentException("Invalid argument");
    }

     */


//int actual = calc.add("1,-1,2,-3");
//int expected = ;

//IllegalArgumentException
//Ille(expected, actual);