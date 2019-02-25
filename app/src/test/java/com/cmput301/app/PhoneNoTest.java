package com.cmput301.app;

import org.junit.Test;
import static org.junit.Assert.*;


public class PhoneNoTest {
    @Test
    public void testContructor() {
        PhoneNo aPhone = new PhoneNo(123,456,4555);
        assertEquals((long)aPhone.getAreaCode(), 123);
        assertEquals((long)aPhone.getFirstDigits(), 456);
        assertEquals((long)aPhone.getLastDigits(),  4555);
    }
    @Test
    public void testToString() {
        PhoneNo aPhone = new PhoneNo(123,456,4555);
        assertEquals(aPhone.toString(), "123-456-4555");

    }
    @Test
    public void testSetPhoneNo() {
        PhoneNo aPhone = new PhoneNo(123,456,4555);
        aPhone.setPhoneNo(555,192,1234);
        assertEquals((long)aPhone.getFirstDigits(), 192);
        assertEquals((long)aPhone.getAreaCode(),  123);
        assertEquals((long)aPhone.getLastDigits(),  4555);
    }




}
