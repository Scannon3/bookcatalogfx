package org.example;

public abstract class Book {
    private String title;
    private String author;
    private String isbn;
    private String bookContent = "This is the books content";
    private boolean isRead;


    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isRead = false;
        this.bookContent = "";
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public void setBookContent(String bookContent) {
        this.bookContent = bookContent;
    }
    public String getBookContent() {
        return bookContent;
    }

    public abstract String getDetails();
}
