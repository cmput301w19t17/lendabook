package com.example.android.lendabook;

public class PhoneNo {

    private Integer areaCode;
    private Integer firstDigits;
    private Integer lastDigits;

    public PhoneNo(Integer areaCode, Integer firstDigits, Integer lastDigits) {
        this.areaCode = areaCode;
        this.firstDigits = firstDigits;
        this.lastDigits = lastDigits;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public Integer getFirstDigits() {
        return firstDigits;
    }

    public Integer getLastDigits() {
        return lastDigits;
    }

    public void setPhoneNo(int areaCode,int firstDigits, int lastDigits) {
        this.areaCode=areaCode;
        this.firstDigits=firstDigits;
        this.lastDigits=lastDigits;
    }
    public String getPhoneNo(){
        return (Integer.toString(areaCode)+ '-' + Integer.toString(firstDigits)+'-' +
                Integer.toString(lastDigits));
    }

    @Override
    public String toString() {
        return null;
    }
}
