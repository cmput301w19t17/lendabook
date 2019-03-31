package com.example.android.lendabook;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by kostin on 2019-03-04.
 * Class for user lists for owned, borrowed, requested and accepted requests.
 */

/**
 * The class to get user list owned, borrowed, requested and accepted requests.
 */

public class User {
    private String username;
    private ContactInfo contactInfo;
    private ArrayList<Book> booksOwned;
    private ArrayList<Book> booksBorrowed = new ArrayList<Book>();
    private ArrayList<Book> booksRequested = new ArrayList<Book>();
    private ArrayList<Book> acceptedRequests = new ArrayList<Book>();
    
    /**
     * Saves the lists in an object User.
     *
     * @param username
     * @param contactInfo
     */
    public User(String username, ContactInfo contactInfo) {
        this.username = username;
        this.contactInfo = contactInfo;
        this.booksOwned = new ArrayList<Book>();
        this.booksBorrowed = new ArrayList<Book>();
        this.booksRequested = new ArrayList<Book>();
        this.acceptedRequests = new ArrayList<Book>();
    }

    public void editContactInfo() {}
    
    /**
     * deletes book
     *
     * @param book
     */
    public void deleteBook(Book book){
        booksOwned.remove(book);
    }
    
    /**
     * edits book
     *
     * @param book
     */
    public void editBook(Book book){}
    
    /**
     * adds book
     *
     * @param book
     */
    public void addBook(Book book){
        booksOwned.add(book);
    }
    
    /**
     * description of book
     *
     * @param book
     */
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
