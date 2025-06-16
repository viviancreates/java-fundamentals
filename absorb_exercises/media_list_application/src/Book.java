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

    public int getpageCount() {
        return pageCount;
    }

    private void String setAuthor(String author) {
        this.author = author;
    }

    private void int setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
