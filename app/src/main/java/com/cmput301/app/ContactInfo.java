package com.cmput301.app;

public class ContactInfo {
    private String name;
    private Integer age;
    private PhoneNo phoneNo;
    private CurrAddress currAddress;
    private String email;

    public ContactInfo(String name, Integer age, PhoneNo phoneNo, CurrAddress currAddress, String email) {
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
        this.currAddress = currAddress;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public PhoneNo getPhoneNo() {
        return phoneNo;
    }

    public CurrAddress getCurrAddress() {
        return currAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPhoneNo(PhoneNo phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setCurrAddress(CurrAddress currAddress) {
        this.currAddress = currAddress;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
