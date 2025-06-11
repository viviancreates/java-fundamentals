public class FizzBuzz {
    UserIO io = new UserIO();

    public void executeFizzBuzz() {
        //print numbers from 1 to 100
        for (int i = 1; i <= 100; i++) {
            //if a number is divisible by both 3 and 5, print "Fizzbuzz" instead of the number
            if (i % 3 == 0 && i % 5 == 0) {
                io.print("Fizzbuzz");

                //if a number is divisible by 3, print "Fizz" instead of the number
            } else if (i % 3 == 0) {
                io.print("Fizz");

                //if a number is divisible by 5, print "Buzz" instead of a number

            }else if (i % 5 == 0) {
                io.print("Buzz");
            } else {
                io.print("not valid");

            }

        }
    }

}
