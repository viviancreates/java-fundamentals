package com.example.model;

public class BookShelfItem {
    private Book book;
    private int position;

    public BookShelfItem(Book book, int position) {
        this.book = book;
        this.position = position;

    }

    public Book getBook() {
        return this.book;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
