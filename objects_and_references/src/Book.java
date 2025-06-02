public class Book {
    String title;
    String author;
    boolean isAvailable;

    //create constructor, initialize variables
    public Book(String title, String author, boolean isAvailable) {
        this.title = title;
        this.author = author;
        //when working with boolean, make this t or f -> this means all books start as available
        this.isAvailable = true;
    }

    public void borrowBook() {
        isAvailable = false;
    }

    public void displayStatus() {
        System.out.println("Book: " + title + "by " + author + " (Available: " + isAvailable + ")");
    }
}
