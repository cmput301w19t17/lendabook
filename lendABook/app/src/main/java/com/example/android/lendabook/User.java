package com.example.android.lendabook;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String username;
    private ContactInfo contactInfo;
    private ArrayList<Book> booksOwned;
    private ArrayList<Book> booksBorrowed = new ArrayList<Book>();
    private ArrayList<Book> booksRequested = new ArrayList<Book>();
    private ArrayList<Book> acceptedRequests = new ArrayList<Book>();

    public User(String username, ContactInfo contactInfo) {
        this.username = username;
        this.contactInfo = contactInfo;
        this.booksOwned = new ArrayList<Book>();
        this.booksBorrowed = new ArrayList<Book>();
        this.booksRequested = new ArrayList<Book>();
        this.acceptedRequests = new ArrayList<Book>();
    }

    public void editContactInfo() {}

    public void deleteBook(Book book){
        booksOwned.remove(book);
    }

    public void editBook(Book book){}

    public void addBook(Book book){
        booksOwned.add(book);
    }

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
