package com.example.android.lendabook;

public class Book {
    private String ISBN;
    private String author;
    private String title;
    private String description;
    private String status;
    private String ownerUsername;
    private String borrower;
    private String firebaseID;

   /* public Book(int ISBN, String author, String title) {
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
    }*/

    public Book(String ISBN, String author, String title, String description, String status, String borrower, String firebaseID) {
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
        this.description = description;
        this.status = status;
        this.borrower = borrower;
        this.firebaseID = firebaseID;

    }

    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author + "\nISBN: " + ISBN + "\nDescription: " + description +"\nStatus: " + status +"\nBorrower: " + borrower;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getBorrower() {
        return borrower;
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

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setFirebaseID(String firebaseID) {
        this.author = firebaseID;
    }

    public String getFirebaseID() {
        return firebaseID;
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
