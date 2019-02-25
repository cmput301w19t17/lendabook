package com.cmput301.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testConstructor() {
        ContactInfo contactStuff = new ContactInfo("personA",20,new PhoneNo(111,111,1111),
                new CurrAddress("Canada","Alberta","Edmonton","1234 1st street"),"abc@ualberta.ca");

        User aUser = new User("username",contactStuff);
        assertEquals("personA",aUser.getUsername());
        assertEquals(contactStuff,aUser.getContactInfo());
    }

    @Test
    public void testEditContactInfo() {
        ContactInfo contactStuff = new ContactInfo("personA",20,new PhoneNo(111,111,1111),
                new CurrAddress("Canada","Alberta","Edmonton","1234 1st street"),"abc@ualberta.ca");

        User aUser = new User("username",contactStuff);

        aUser.getContactInfo().setAge(19);
        assertEquals(25,(long)aUser.getContactInfo().getAge());

        aUser.getContactInfo().setEmail("def@gmail.com");
        assertEquals("def@gmail.com",aUser.getContactInfo().getEmail());

        aUser.getContactInfo().setName("personB");
        assertEquals("personB",aUser.getContactInfo().getName());

        CurrAddress anotherAddress = new CurrAddress("Acountry","Aprovince","Acity","AHouse");
        aUser.getContactInfo().setCurrAddress(anotherAddress);
        assertEquals( anotherAddress,aUser.getContactInfo().getCurrAddress());

        PhoneNo anotherPhone = new PhoneNo(403,403,4033);
        aUser.getContactInfo().setPhoneNo(anotherPhone);
        assertEquals(anotherPhone,aUser.getContactInfo().getPhoneNo());
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
