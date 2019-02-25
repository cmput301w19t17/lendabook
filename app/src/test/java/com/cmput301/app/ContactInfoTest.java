package com.cmput301.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class ContactInfoTest {
    @Test
    public void testConstructor() {
        CurrAddress newAddress = new CurrAddress("Canada","Alberta","Edmonton","1234 1st street");
        PhoneNo aPhoneNo = new PhoneNo(111,145,1341);

        ContactInfo contactStuff = new ContactInfo("personA",99,aPhoneNo, newAddress,"abc@ualberta.ca");
        assertEquals(contactStuff.getName(),"personA");
        assertEquals(contactStuff.getCurrAddress(), newAddress);
        assertEquals(contactStuff.getPhoneNo(), aPhoneNo);
        assertEquals(contactStuff.getEmail(),"abc@ualberta.ca");
        assertEquals((long)contactStuff.getAge(), 99);

    }

    @Test
    public void TestSetName() {
        CurrAddress newAddress = new CurrAddress("Canada","Alberta","Edmonton","1234 1st street");
        PhoneNo aPhoneNo = new PhoneNo(111,145,1341);

        ContactInfo contactStuff = new ContactInfo("personA",99,aPhoneNo, newAddress,"abc@ualberta.ca");

        contactStuff.setName("Bperson");
        assertEquals(contactStuff.getName(),"Bperson");
    }

    @Test
    public void TestSetAge() {
        CurrAddress newAddress = new CurrAddress("Canada","Alberta","Edmonton","1234 1st street");
        PhoneNo aPhoneNo = new PhoneNo(111,145,1341);

        ContactInfo contactStuff = new ContactInfo("personA",99,aPhoneNo, newAddress,"abc@ualberta.ca");
        contactStuff.setAge(23);
        assertEquals((int)contactStuff.getAge(),23);
    }

    @Test
    public void TestSetAddress() {
        CurrAddress newAddress = new CurrAddress("Canada","Alberta","Edmonton","1234 1st street");
        PhoneNo aPhoneNo = new PhoneNo(111,145,1341);

        ContactInfo contactStuff = new ContactInfo("personA",99,aPhoneNo, newAddress,"abc@ualberta.ca");
        CurrAddress anotherAddress = new CurrAddress("anothercountry","anotherprovince","anothercity","anotherstreet");
        contactStuff.setCurrAddress(anotherAddress);
        assertEquals(contactStuff.getCurrAddress(),anotherAddress);
    }

    @Test
    public void TestSetPhoneNo() {
        CurrAddress newAddress = new CurrAddress("Canada","Alberta","Edmonton","1234 1st street");
        PhoneNo aPhoneNo = new PhoneNo(111,145,1341);

        ContactInfo contactStuff = new ContactInfo("personA",99,aPhoneNo, newAddress,"abc@ualberta.ca");

        PhoneNo anotherPhone = new PhoneNo(403,444,1920);

        contactStuff.setPhoneNo(anotherPhone);
        assertEquals(contactStuff.getPhoneNo(),anotherPhone);
    }

    @Test
    public void TestSetEmail() {
        CurrAddress newAddress = new CurrAddress("Canada","Alberta","Edmonton","1234 1st street");
        PhoneNo aPhoneNo = new PhoneNo(111,145,1341);

        ContactInfo contactStuff = new ContactInfo("personA",99,aPhoneNo, newAddress,"abc@ualberta.ca");

        contactStuff.setEmail("email@email.com");
        assertEquals(contactStuff.getEmail(),"email@email.com");
    }


}
