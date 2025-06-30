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