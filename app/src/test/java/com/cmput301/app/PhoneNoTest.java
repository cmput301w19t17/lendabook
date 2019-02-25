package com.cmput301.app;

import org.junit.Test;
import static org.junit.Assert.*;


public class PhoneNoTest {
    @Test
    public void testContructor() {
        PhoneNo aPhone = new PhoneNo(123,456,4555);
        assertEquals(123,(long)aPhone.getAreaCode());
        assertEquals(456,(long)aPhone.getFirstDigits());
        assertEquals(4555,(long)aPhone.getLastDigits());
    }
    @Test
    public void testToString() {
        PhoneNo aPhone = new PhoneNo(123,456,4555);
        assertEquals("123-456-4555",aPhone.toString());

    }
    @Test
    public void testSetPhoneNo() {
        PhoneNo aPhone = new PhoneNo(123,456,4555);
        aPhone.setPhoneNo(555,192,1234);
        assertEquals(192,(long)aPhone.getFirstDigits());
        assertEquals(555,(long)aPhone.getAreaCode());
        assertEquals(1234,(long)aPhone.getLastDigits());
    }




}
