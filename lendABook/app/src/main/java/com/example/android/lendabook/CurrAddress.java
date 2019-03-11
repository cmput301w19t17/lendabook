package com.example.android.lendabook;

/**
 * Created by belachew on 2019-02-27.
 * Class for current address.
 */

/**
 * The class to get information about current address
 */

public class CurrAddress {
    private String country;
    private String city;
    private String stateProvince;
    private String houseNo;
    
    /**
     * Gets info about current address
     *
     * @param country
     * @param stateProvince
     * @param city
     * @param houseNo
     */

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
