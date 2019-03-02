package com.example.android.lendabook;

import com.example.android.lendabook.CurrAddress;

import org.junit.Test;
import static org.junit.Assert.*;


public class CurrAddressTest {

    @Test
    public void constructorTest() {
        CurrAddress anAddress = new CurrAddress("aCountry", "aProvince","aCity", "aHouse");
        assertEquals("aCountry",anAddress.getCountry());
        assertEquals("aCity",anAddress.getCity());
        assertEquals("aHouse",anAddress.getHouseNo());
        assertEquals("aProvince",anAddress.getStateProvince());
    }

    @Test
    public void TestSetCountry() {
        CurrAddress anAddress = new CurrAddress("aCountry", "aProvince","aCity", "aHouse");
        anAddress.setCountry("Canada");
        assertEquals("Canada",anAddress.getCountry());
    }

    @Test
    public void TestSetCity() {
        CurrAddress anAddress = new CurrAddress("aCountry", "aProvince","aCity", "aHouse");
        anAddress.setCity("Edmonton");
        assertEquals("Edmonton",anAddress.getCity());
    }

    @Test
    public void TestSetHouseNo() {
        CurrAddress anAddress = new CurrAddress("aCountry", "aProvince","aCity", "aHouse");
        anAddress.setHouseNo("123 1st street");
        assertEquals("123 1st street",anAddress.getHouseNo());
    }

    @Test
    public void TestSetProvince() {
        CurrAddress anAddress = new CurrAddress("aCountry", "aProvince","aCity", "aHouse");
        anAddress.setStateProvince("Alberta");
        assertEquals("Alberta", anAddress.getStateProvince());
    }
}