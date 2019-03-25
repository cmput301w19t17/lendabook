package com.example.android.lendabook;

/**
 * Created by kostin on 2019-02-27.
 * Class for saving/storying book information.
 */

/**
 * Class to store and initalize and object of a book
 */

public class Book {
    //info about book we will be storing.
    private String title;
    private String isbn;
    private String author;
    private String description;
    private String owner;
    private String borrower;
    private String status;
    /**
     * Saves the book information.
     *
     * @param title Boot Title
     */
    public Book(String title, String isbn, String author,  String description, String owner, String borrower, String status) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.description = description;
        this.owner = owner;
        this.borrower = borrower;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author + "\nDescription: " + description +"\nBorrower: " + owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
