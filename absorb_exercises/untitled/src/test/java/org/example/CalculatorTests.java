package org.example;

public class CalculatorTests {
    private StringCalculator calc

    @BeforeEach
    void setup() { calc = new StringCalculator();}

    @Test
    @DisplayName("Empty string returns zero")
    void emptyStringReturnZero() {
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
    @DisplayName("two numbers returns sum")
    void  twoNumbersReturnsSum() {
        int actual = calc.add("5, 3");
        int expected = 8;

        assertEquals(expected, actual);
    }
}
