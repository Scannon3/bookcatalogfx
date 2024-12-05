package org.example;

public class PrintBook extends Book {
    private int pages;

    public PrintBook(String title, String author, String isbn, int pages) {
        super(title, author, isbn);
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String getDetails() {
        return pages+" pages";
    }
}