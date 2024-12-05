package org.example;

public class AudioBook extends Book {
    private int durationMinutes;

    public AudioBook(String title, String author, String isbn, int durationMinutes) {
        super(title, author, isbn);
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String getDetails() {
        return durationMinutes+" min" ;
    }
}