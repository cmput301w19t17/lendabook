package com.cmput301.app;

import java.util.ArrayList;

public class User {

    private String username;
    private ContactInfo contactInfo;
    private ArrayList<Book> booksOwned;
    private ArrayList<Book> booksBorrowed;
    private ArrayList<Book> booksRequested;
    private ArrayList<Book> acceptedRequests;

    public User(String username, ContactInfo contactInfo) {
        this.username = username;
        this.contactInfo = contactInfo;
    }

    public void editContactInfo() {}

    public void deleteBook(Book book){}

    public void editBook(Book book){}

    public void addBook(Book book){}

    public String getDescription(Book book){ return "";}

    public String getUsername() {
        return username;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public ArrayList<Book> getBooksOwned() {
        return booksOwned;
    }

    public ArrayList<Book> getBooksBorrowed() {
        return booksBorrowed;
    }

    public ArrayList<Book> getBooksRequested() {
        return booksRequested;
    }

    public ArrayList<Book> getAcceptedRequests() {
        return acceptedRequests;
    }
}
