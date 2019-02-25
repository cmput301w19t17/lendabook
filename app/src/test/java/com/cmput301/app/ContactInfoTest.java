package com.cmput301.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class ContactInfoTest {
    @Test
    public void testConstructor() {
        CurrAddress newAddress = new CurrAddress("Canada","Alberta","Edmonton","1234 1st street");
        PhoneNo aPhoneNo = new PhoneNo(111,145,1341);

        ContactInfo contactStuff = new ContactInfo("personA",99,aPhoneNo, newAddress,"abc@ualberta.ca");
        assertEquals("personA",contactStuff.getName());
        assertEquals(newAddress,contactStuff.getCurrAddress());
        assertEquals(aPhoneNo, contactStuff.getPhoneNo());
        assertEquals("abc@ualberta.ca",contactStuff.getEmail());
        assertEquals(99,(long)contactStuff.getAge());

    }

    @Test
    public void TestSetName() {
        CurrAddress newAddress = new CurrAddress("Canada","Alberta","Edmonton","1234 1st street");
        PhoneNo aPhoneNo = new PhoneNo(111,145,1341);

        ContactInfo contactStuff = new ContactInfo("personA",99,aPhoneNo, newAddress,"abc@ualberta.ca");

        contactStuff.setName("Bperson");
        assertEquals("Bperson",contactStuff.getName());
    }

    @Test
    public void TestSetAge() {
        CurrAddress newAddress = new CurrAddress("Canada","Alberta","Edmonton","1234 1st street");
        PhoneNo aPhoneNo = new PhoneNo(111,145,1341);

        ContactInfo contactStuff = new ContactInfo("personA",99,aPhoneNo, newAddress,"abc@ualberta.ca");
        contactStuff.setAge(23);
        assertEquals(23,(int)contactStuff.getAge());
    }

    @Test
    public void TestSetAddress() {
        CurrAddress newAddress = new CurrAddress("Canada","Alberta","Edmonton","1234 1st street");
        PhoneNo aPhoneNo = new PhoneNo(111,145,1341);

        ContactInfo contactStuff = new ContactInfo("personA",99,aPhoneNo, newAddress,"abc@ualberta.ca");
        CurrAddress anotherAddress = new CurrAddress("anothercountry","anotherprovince","anothercity","anotherstreet");
        contactStuff.setCurrAddress(anotherAddress);
        assertEquals(anotherAddress, contactStuff.getCurrAddress());
    }

    @Test
    public void TestSetPhoneNo() {
        CurrAddress newAddress = new CurrAddress("Canada","Alberta","Edmonton","1234 1st street");
        PhoneNo aPhoneNo = new PhoneNo(111,145,1341);

        ContactInfo contactStuff = new ContactInfo("personA",99,aPhoneNo, newAddress,"abc@ualberta.ca");

        PhoneNo anotherPhone = new PhoneNo(403,444,1920);

        contactStuff.setPhoneNo(anotherPhone);
        assertEquals(anotherPhone,contactStuff.getPhoneNo());
    }

    @Test
    public void TestSetEmail() {
        CurrAddress newAddress = new CurrAddress("Canada","Alberta","Edmonton","1234 1st street");
        PhoneNo aPhoneNo = new PhoneNo(111,145,1341);

        ContactInfo contactStuff = new ContactInfo("personA",99,aPhoneNo, newAddress,"abc@ualberta.ca");

        contactStuff.setEmail("email@email.com");
        assertEquals("email@email.com",contactStuff.getEmail());
    }




}
