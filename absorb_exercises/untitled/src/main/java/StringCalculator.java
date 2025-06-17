package org.example
public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else if (!numbers.contains(",")) {
            return Integer.parseInt(numbers);
        } else {
            String[] nums = numbers.split(",");
            return getSum(nums);
        }
    }

    private int getSum(String[] nums) {
        int sum = 0

                for(String num:nums) {
                    sum += Integer.parseInt(num);
                }
                return sum;
    }
}
