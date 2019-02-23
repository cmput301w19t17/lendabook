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

    
}
