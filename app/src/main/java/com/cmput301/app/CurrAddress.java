package com.cmput301.app;

public class CurrAddress {

    private String country;
    private String city;
    private String houseNo;

    public CurrAddress(String country, String city, String houseNo) {
        this.country = country;
        this.city = city;
        this.houseNo = houseNo;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getHouseNo() {
        return houseNo;
    }
}
