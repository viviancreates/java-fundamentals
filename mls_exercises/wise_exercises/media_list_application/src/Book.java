public class Book extends Media {
    private String author;
    private int pageCount;

    public Book(String author, int pageCount, String name) {
        this.author = author;
        this.pageCount = pageCount;
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }
I
    public int getpageCount() {
        return pageCount;
    }

    private void setAuthor(String author) {
        this.author = author;
    }

    private void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    //add abstract methodds from media with override
    //Book: "Opening book '[name]' using e-reader software"
    @Override
    public void play(){
        System.out.println("Opening book " + name + " using e-reader software.");
    };
    public String getDescription(){
        return "Book: "+ name + "\nAuthor: " + author +"\nPage Count: " + pageCount;
    };
}
