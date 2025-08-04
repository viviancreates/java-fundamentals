import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //defailt working directory is the folder called FileIODemo
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        //IF there is a file type, give it a name
        //path can be absolute or relative -> relative is relative to where you are, start where the app is running
        // absolute start at root of where the folder is
        File file = new File("data/input.txt");

//        //DEMO 0
//        //check for existence
//        System.out.println(file.exists());
//        System.out.println(file.isFile());
//        System.out.println(file.isDirectory());
//        //bytes
//        //how many string characters in a byte -> usually 1 unless emoji or characters(between 1-4 depending on characters)
//        System.out.println(file.length());
//
//        //DEMO 1
//        File file2 = new File("data/poem.txt");
//        //when reading and writing to files, put the work in a try catch
//        //now we use methods that will throw exceptions -> so we need to start using
//        try {
//            //THIS CREATED A NEW FILE called poem.txt
//            boolean created = file2.createNewFile();
//            System.out.println(file2.getAbsolutePath()); //if you want to know the absolute path of the file, this how you get it
//            scanner.nextLine(); //this pauses
//            boolean deleted = file2.delete(); //deletes the file
//        } catch (IOException e) {
//            System.out.println("Couldn't create file!");
//        }

//        //DEMO 2
//        try {
//            //new keyword will open the file
//            FileWriter writer = new FileWriter("data/output.txt");
//            writer.write("Hello, World!\n");
//            writer.write("Today is a good day for Java!\n");
//            writer.write("Happy June!\n");
//            writer.close();
//        } catch (IOException e) {
//            System.out.println(e);
//        }

//        //DEMO 3
//        //PrintWriter
//        try {
//            PrintWriter writer = new PrintWriter("data/output1.txt");
//            //If you use writer.println vs writer.write.. you do not need to add /n (newlines) manually at the end
//            writer.println("Hello, World!");
//            writer.println("Today is a good day for Java!");
//            writer.println("Happy June!");
//            writer.print("This will not have a carriage return | ");
//            // if you want to format the text that comes out, you can use a printf here
//            writer.printf("Number: %d%n", 15);
//
//            writer.close();
//        } catch (IOException e) {
//            System.out.println(e);
//        }

//        //DEMO 4
//        //FileReader
//        //reads the next character in a file until there is no other character
//        // text files end with a nujll character a /0 which returns negtive one
//        // so you use a while loop, and you read the characters one at a time, that includes the newlines
//        // - it gets one character a t a time until it hits the null terminator, then it returns -1 and it is done reading
//        try {
//            FileReader reader = new FileReader("data/output1.txt");
//            int charCode;
//            while((charCode = reader.read()) != -1) {
//                char c = (char) charCode;
//                System.out.print(c);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println(e);
//        } catch (IOException e) {
//            System.out.println(e);
//        }

//        //DEMO 5
//        //Still used the FileReader under the covers, so when you created a buffered reader, you still give it a filereader object to use
//        //soo.. the buffered reader takes the file reader as a dependency
//        //.. and the file reader points at the fie you want to get the data from
//        //but.. it reads whole lines instead of one character at a time
//        //.....
//        //reader.read().. if you wanted to read one character
//        //            //reader.ready()... if the file is open
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("data/output.txt"));
//            String line;
//
//            //when it reached the end of the file, it returns a null instead of negative 1
//            //read until it gets to carriage return or null
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            reader.close();
//        } catch (IOException e) {
//            System.out.println(e);
//        }

//        // DEMO 6
//        //Let's go back to the print writer
//        // Java's long winded way of doing 'with' (python with statement) -> try-with-resources
//        //do not need a reader.close anymore
//        //this is the best way to work with files, so it gets cleaned up automatically when you are done
//        try (BufferedReader reader = new BufferedReader(new FileReader("data/output.txt"))) {
//            //
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//        // we are done learning details, now we are creating objects and calling their methods
//        // all we need to know is a few things
//        // what its called -> BufferedReader
//        // need to know how to create it ->  new BufferedReader(new FileReader("data/output.txt"))
//        // what methods are available -> reader.readLine()




        /*
        //hexadecimel example
        - you can cram bigger numbers into a smaller space
        //this is what identifies as a png -> first 4
        // 89 50 4E 47

        //the rest tells it pixels etc
        //this is an image
        89 50 4E 47 0D 0A 1A 0A 00 00 00 0D 49 48 44 52
        00 00 01 90 00 00 01 2C 08 02 00 00 00 A0 9A 8E
        27 00 00 00 09 70 48 59 73 00 00 0B 13 00 00 0B

        0-9
        0-F
        0 1 2 3 4 5 6 7 8 9 A B C D E F
        50 = 5 * 16 + 0 = 80
        4E = 4 * 16 + 14 = 78
        0 - 255
        FF
        22 = 34
        AB = 171
        10 = 16
         */
    }
}