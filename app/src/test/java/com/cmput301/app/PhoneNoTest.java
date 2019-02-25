package com.cmput301.app;

import org.junit.Test;
import static org.junit.Assert.*;


public class PhoneNoTest {

    public void testContructor() {
        PhoneNo aPhone = new PhoneNo(123,456,4555);
        assertEquals(aPhone.getAreaCode(),(Integer) 123);
        assertEquals(aPhone.getFirstDigits(),(Integer) 456);
        assertEquals(aPhone.getLastDigits(), (Integer) 4555);
    }

    public void testToString() {
        PhoneNo aPhone = new PhoneNo(123,456,4555);
        assertEquals(aPhone.toString(), "123-456-4555");

    }

    public void testSetPhoneNo() {
        PhoneNo aPhone = new PhoneNo(123,456,4555);
        aPhone.setPhoneNo(555,192,1234);
        assertEquals(aPhone.getFirstDigits(),(Integer) 192);
        assertEquals(aPhone.getAreaCode(), (Integer) 123);
        assertEquals(aPhone.getLastDigits(), (Integer) 4555);
    }

    


}
