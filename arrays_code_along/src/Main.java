public class Main {
    public static void main(String[] args) {
        System.out.println("Arrays Demo - Classroom Manager");

        //start with 5 students
        String s1= "Jerry";
        String s2 = "Elaine";
        String s3= "George";
        String s4 = "Kramer";
        String s5 = "Newman";

        //print welcome to each student
        System.out.println("Hello, "+s1);
        System.out.println("Hello, "+s2);
        System.out.println("Hello, "+s3);
        System.out.println("Hello, "+s4);
        System.out.println("Hello, "+s5);

        //step1 - declare an array
        //define an array variable and assign the variable a new instance of an array of a specified size
        String[] students;
        students = new String[5];

        //step2 - assign values to an array
        //once the array is defined, assign values to the elements
        students[0] = "Jerry";
        students[1] = "Elaine";
        students[2] = "George";
        students[3] = "Kramer";
        students[4] = "Newman";

        //define an array and assign values at the same time
        //since we do not provide size of the array, the compiler calculates based on number of values assigned
        //when it generates the array object
        double[] grades = {88.5, 97.8, 92.2, 78.7, 80.1};

        //access elements in an array
        System.out.println("3rd student = " + students[2]);

        //print out final element in an array
        //use the array's length property and subtract one from it
        System.out.println("Last student = " + students[students.length - 1]);

        //loop over the elements in an array
        //use loop to print each student and their grades
        for (int i = 0; i < students.length; i++) {
            System.out.println("Student [" + i + "]) = " + students[i] + ", grade = " + grades[i]);
        }
        //students.length: Loops through each index of the students array
        //System.out.println(...): Prints each student's name and grade on a separate line
        //"Student [" + i + "] = " + students[i]: Shows the index and the student's name
        //", grade = " + grades[i]: Shows the corresponding grade for that student

        //when there is an array of numeric values, you can loop across the array to perform math operations
        //like sum and averages
        //average grades
        double sum = 0.0;
        for (int i = 0; i < grades.length; i++){
            sum += grades[i];
        }
        System.out.println("Grade average = "+ (sum / grades.length));
    }
}