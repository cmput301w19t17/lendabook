package com.cmput301.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testConstructor() {
        ContactInfo contactStuff = new ContactInfo("personA",20,new PhoneNo(111,111,1111),
                new CurrAddress("Canada","Alberta","Edmonton","1234 1st street"),"abc@ualberta.ca");

        User aUser = new User("username",contactStuff);
        assertEquals(aUser.getUsername(),"personA");
        assertEquals(aUser.getContactInfo(), contactStuff);
    }

    @Test
    public void testEditContactInfo() {
        ContactInfo contactStuff = new ContactInfo("personA",20,new PhoneNo(111,111,1111),
                new CurrAddress("Canada","Alberta","Edmonton","1234 1st street"),"abc@ualberta.ca");

        User aUser = new User("username",contactStuff);

        aUser.getContactInfo().setAge(19);
        assertEquals(aUser.getContactInfo().getAge(),(Integer) 25);

        aUser.getContactInfo().setEmail("def@gmail.com");
        assertEquals(aUser.getContactInfo().getEmail(),"def@gmail.com");

        aUser.getContactInfo().setName("personB");
        assertEquals(aUser.getContactInfo().getName(), "personB");

        CurrAddress anotherAddress = new CurrAddress("Acountry","Aprovince","Acity","AHouse");
        aUser.getContactInfo().setCurrAddress(anotherAddress);
        assertEquals(aUser.getContactInfo().getCurrAddress(), anotherAddress);

        PhoneNo anotherPhone = new PhoneNo(403,403,4033);
        aUser.getContactInfo().setPhoneNo(anotherPhone);
        assertEquals(aUser.getContactInfo().getPhoneNo(),anotherPhone);
    }

    @Test
    public void testAddAndDeleteBook() {
        Book newBook = new Book(123,"abc","title");
        ContactInfo contactStuff = new ContactInfo("personA",20,new PhoneNo(111,111,1111),
                new CurrAddress("Canada","Alberta","Edmonton","1234 1st street"),"abc@ualberta.ca");
        User aUser = new User("username",contactStuff);
        aUser.getBooksOwned().add(newBook);
        assertTrue(aUser.getBooksOwned().contains(newBook));
        aUser.getBooksOwned().remove(newBook);
        assertFalse(aUser.getBooksOwned().contains(newBook));
    }


}
