package com.cmput301.app;

public class Book {

    private int ISBN;
    private String author;
    private String title;
    private String description;
    private String status;
    private String ownerUsername;

    public Book(int ISBN, String author, String title) {
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
    }

    public Book(int ISBN, String author, String title, String description) {
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
        this.description = description;
    }


    public int getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
}
