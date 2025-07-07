# Reading and writing to files 
- There is a file that has a path, I need the path to get to the file
- Check if it exists, before I open
- In java, there is a file type

## DEMO 0
- Create interfaces whenever we read and write to files
  - Need testing paths and productions paths -> so the behavior of reading a file will be the same but the implementation will be different
  - Create a service behind a service so we we could read the files local before changing 
  - Another way would be configuration files
```
import java.io.File;

        //check for existence
        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        //bytes
        //how many string characters in a byte -> usually 1 unless emoji or characters(between 1-4 depending on characters)
        System.out.println(file.length());
```

## DEMO 1
- A lot of helpful methods in the file object that give control over things you can do 
```
import java.io.IOException;

  File file2 = new File("data/poem.txt");
        //when reading and writing to files, put the work in a try catch
        //now we use methods that will throw exceptions -> so we need to start using
        try {
            //THIS CREATED A NEW FILE called poem.txt
            boolean created = file2.createNewFile();
            System.out.println(file2.getAbsolutePath()); //if you want to know the absolute path of the file, this how you get it
            scanner.nextLine(); //this pauses
            boolean deleted = file2.delete(); //deletes the file
        } catch (IOException e) {
            System.out.println("Couldn't create file!");
        }

```

## DEMO 2 - FileWriter
- If I want to write to a file, there are two classes that can be used, depending on what you want to do 
- If you run it again, it overwrites everything, deletes the old content, adds new 
  - Completely replaces file, by default
- If you want to format strings, you cannot use print f so you would just put format.string inside()
  - this is why people prefer printwriter -> to use print f (demo 3)
```
import java.io.FileWriter;

 try {
            //new keyword will open the file
            FileWriter writer = new FileWriter("data/output.txt");
            writer.write("Hello, World!\n");
            writer.write("Today is a good day for Java!\n");
            writer.write("Happy June!\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
```

## DEMO 3 - PrintWriter 
- Does what?
  - If you use writer.println vs writer.write.. you do not need to add /n (newlines) manually at the end
  - Also, it gives a printf if you want to format the text that comes out
- print — no newline 
- println — adds \n 
- printf — you control newline with %n

```
import java.io.PrintWriter;

  try {
            PrintWriter writer = new PrintWriter("data/output1.txt");
            //If you use writer.println vs writer.write.. you do not need to add /n (newlines) manually at the end
            writer.println("Hello, World!");
            writer.println("Today is a good day for Java!");
            writer.println("Happy June!");
            writer.print("This will not have a carriage return | ");
            writer.printf("Number: %d%n", 15);

            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
```

## DEMO 4 - FileReader
- `FileReader` reads a file **one character at a time**
- Text files end with a **null terminator (`\0`)**, so when reading:
  - `read()` returns `-1` when there are no more characters
- Use a `while` loop:
  - Keep reading until `read()` returns `-1`
  - Each `read()` includes spaces and newlines
- Cast the `int` to `char` to print the actual character

```
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

 try {
            FileReader reader = new FileReader("data/output.txt");
            int charCode;
            while((charCode = reader.read()) != -1) {
                char c = (char) charCode;
                System.out.print(c);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
```

## DEMO 5 - BufferedReader
- `BufferedReader` reads whole lines at a time, instead of one character at a time
- It wraps a `FileReader`, so you pass a `FileReader` as a dependency
  - `BufferedReader` uses `FileReader` under the hood
  - `FileReader` points to the file to read
- Use `readLine()`:
  - Returns the next line as a `String`
  - Returns `null` when it reaches the end of the file
- Unlike `read()`, which returns `-1` for EOF, `readLine()` uses `null`
- Use a `while` loop:
  - Keep reading lines until `readLine()` returns `null`
- **Other useful methods:**
  - `reader.read()` — read one character at a time (not line-based)
  - `reader.ready()` — checks if the stream is ready to be read (if the file is open and data is available)
  
```
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

 try {
            BufferedReader reader = new BufferedReader(new FileReader("data/output.txt"));
            String line;

            //when it reached the end of the file, it returns a null instead of negative 1
            //read until it gets to carriage return or null
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }

```

## DEMO 6 - try-with-resources

- Try-with-resources is Java’s version of Python’s `with` statement
- Lets you open a file and have Java automatically close it when you’re done
- Saves you from forgetting reader.close() which can cause bugs or locked files
- Keeps your code cleaner and safer, even if something goes wrong
- Syntax: try (resource) { ... } — the resource goes in the parentheses
- The resource must implement AutoCloseable (like BufferedReader)
- What happens?
  - Java opens the file for you
  - You read it line by line (or however you want)
  - When the try block ends, Java automatically closes the file
```
 try (BufferedReader reader = new BufferedReader(new FileReader("data/output.txt"))) {
            //
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
```

## Finally...
- we are done learning details, now we are creating objects and calling their methods
- all we need to know is a few things
- what its called -> BufferedReader
- need to know how to create it ->  new BufferedReader(new FileReader("data/output.txt"))
- what methods are available -> reader.readLine()

---
- images are binary, if you want to work with anything that is not a textfile, you need to work with binary data
- 