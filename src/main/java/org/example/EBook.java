package org.example;

public class EBook extends Book {
    private double fileSize; // File size in MB

    public EBook(String title, String author, String isbn, double fileSize) {
        super(title, author, isbn);
        this.fileSize = fileSize;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String getDetails() {
        return fileSize + "MB";
    }
}