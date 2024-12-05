package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookCatalog {
    private List<Book> books;

    public BookCatalog() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }
    public boolean removeBook(String isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbn)) {
                books.remove(i);
                return true;
            }
        }
        return false; //
    }
    public void sortBooks() {
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                return book1.getTitle().compareToIgnoreCase(book2.getTitle());
            }
        });
    }
    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Return null if no match is found
    }
    public void markAsRead(String title) {
        Book book = searchBook(title); // Use the searchBook method to find the book
        if (book != null) {
            book.setRead(true); // Mark the book as read
            System.out.println("Marked as read: " + title);
        } else {
            System.out.println("Book not found: " + title);
        }
    }

}