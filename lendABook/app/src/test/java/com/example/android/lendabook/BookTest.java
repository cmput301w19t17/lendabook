package com.example.android.lendabook;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void constructorTest() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        assertEquals(123,aBook.getISBN());
        assertEquals("Joseph Heller", aBook.getAuthor());
        assertEquals("Catch 22", aBook.getTitle());
    }
    @Test
    public void testSetISBN() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        aBook.setISBN(456);
        assertEquals(456, aBook.getISBN() );


    }
    @Test
    public void testSetAuthor() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        aBook.setAuthor("anotherAuthor");
        assertEquals("anotherAuthor",aBook.getAuthor());
    }
    @Test
    public void testSetDescription() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        aBook.setDescription("A book about...");
        assertEquals("A book about...", aBook.getDescription());
    }
    @Test
    public void testSetTitle() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        aBook.setTitle("newTitle");
        assertEquals("newTitle", aBook.getTitle());

    }
    @Test
    public void testSetOwnerUsername() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        aBook.setOwnerUsername("PersonA");
        assertEquals("PersonA",aBook.getOwnerUsername());
    }
    @Test
    public void testSetStatus() {
        Book aBook = new Book(123, "Joseph Heller", "Catch 22");
        aBook.setStatus("Available");
        assertEquals("Available",aBook.getStatus());
    }

}
