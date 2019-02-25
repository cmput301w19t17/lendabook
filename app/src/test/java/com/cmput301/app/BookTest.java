package com.cmput301.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void constructorTest() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        assertEquals(aBook.getISBN(), 123);
        assertEquals(aBook.getAuthor(), "Joseph Heller");
        assertEquals(aBook.getTitle(), "Catch 22");
    }
    @Test
    public void testSetISBN() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        aBook.setISBN(456);
        assertEquals(aBook.getISBN(), 456);


    }
    @Test
    public void testSetAuthor() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        aBook.setAuthor("anotherAuthor");
        assertEquals(aBook.getAuthor(),"anotherAuthor");
    }
    @Test
    public void testSetDescription() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        aBook.setDescription("A book about...");
        assertEquals(aBook.getDescription(),"A book about...");
    }
    @Test
    public void testSetTitle() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        aBook.setTitle("newTitle");
        assertEquals(aBook.getTitle(),"newTitle");

    }
    @Test
    public void testSetOwnerUsername() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        aBook.setOwnerUsername("PersonA");
        assertEquals(aBook.getOwnerUsername(),"PersonA");
    }
    @Test
    public void testSetStatus() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        aBook.setStatus("Available");
        assertEquals(aBook.getStatus(),"Available");
    }




}
