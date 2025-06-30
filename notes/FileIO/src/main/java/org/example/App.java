package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    Scanner scanner = new Scanner(System.in);

//        System.out.println("Working directory: " + System.getProperty("user.dir"));
//        File file = new File("data/input.txt");
//
//        System.out.println(file.exists());
//        System.out.println(file.isFile());
//        System.out.println(file.isDirectory());
//        System.out.println(file.length());
//
//        File file2 = new File("data/poem.txt");
//        try {
//            boolean created = file2.createNewFile();
//            System.out.println(file2.getAbsolutePath());
//            scanner.nextLine();
//            boolean deleted = file2.delete();
//        } catch (IOException e) {
//            System.out.println("Couldn't create file!");
//        }

//        try {
//            FileWriter writer = new FileWriter("data/output.txt");
//            writer.write("Hello, World!\n");
//            writer.write("Today is a good day for Java!\n");
//            writer.write("Happy June!\n");
//            writer.close();
//        } catch (IOException e) {
//            System.out.println(e);
//        }

//        try {
//            PrintWriter writer = new PrintWriter("data/output.txt");
//            writer.println("Hello, World!");
//            writer.println("Today is a good day for Java!");
//            writer.println("Happy June!");
//            writer.print("This will not have a carriage return | ");
//            writer.printf("Number: %d%n", 15);
//
//            writer.close();
//        } catch (IOException e) {
//            System.out.println(e);
//        }

//        try {
//            FileReader reader = new FileReader("data/output.txt");
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

//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("data/output.txt"));
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            reader.close();
//        } catch (IOException e) {
//            System.out.println(e);
//        }

    // Java's long winded way of doing 'with'
        try (BufferedReader reader = new BufferedReader(new FileReader("data/output.txt"))) {
    String line;

    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    System.out.println(e);
}

        /*
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
        89 50 4E 47 0D 0A 1A 0A 00 00 00 0D 49 48 44 52
        00 00 01 90 00 00 01 2C 08 02 00 00 00 A0 9A 8E
        27 00 00 00 09 70 48 59 73 00 00 0B 13 00 00 0B
         */
}
