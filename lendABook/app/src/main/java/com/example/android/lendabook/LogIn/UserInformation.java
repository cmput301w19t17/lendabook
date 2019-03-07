package com.example.android.lendabook.LogIn;

public class UserInformation {

    public String username;
    public String full_name;
    public String website;
    public String description;
    public String phone_number;

    public UserInformation(){

    }

    public UserInformation(String username, String full_name, String website, String description, String phone_number) {
        this.username = username;
        this.full_name = full_name;
        this.website = website;
        this.description = description;
        this.phone_number = phone_number;
    }
}
