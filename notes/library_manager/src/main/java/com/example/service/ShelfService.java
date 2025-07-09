package com.example.service;

import com.example.model.Book;
import java.util.ArrayList;

public class ShelfService {
    private ArrayList<Book> books;
    //capacity is all caos bc its a constanrt
    private final int CAPACITY = 250;

    public ShelfService() {
        this.books = new ArrayList<>();

    }

    public ArrayList<BookShelfItem> getBooks() {
        ArrayList<BookShelfItem> result = new ArrayList<>();

        //only return the books that have been put in the shelf
        for(int i = 0; i < CAPACITY; i++) {
            if (books[i] != null) {
                result.add(new BookShelfItem(books[i], i+1));
            }
        }
        return result;
    }

    public boolean addBook(Book book, int position) {
        ir (position < 0 || position > capacity - 1) {
            return false;
        }

        if(this.books[position] != null) {
            return false;
        }

        this.books[position] = book;
        return true;
    }
}
