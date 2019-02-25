package com.cmput301.app;

public class CurrAddress {

    private String country;
    private String city;
    private String stateProvince;
    private String houseNo;

    public CurrAddress(String country,String stateProvince, String city, String houseNo) {
        this.country = country;
        this.city = city;
        this.houseNo = houseNo;
        this.stateProvince = stateProvince;
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

    public String getStateProvince() {
        return stateProvince;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }
}
