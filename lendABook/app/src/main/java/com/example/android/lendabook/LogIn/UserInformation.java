package com.example.android.lendabook.LogIn;

/**
 * Created by oleg on 2019-03-03.
 * Class for saving user information.
 */

/**
 * The class to save user information so we can store it in the database.
 */

public class UserInformation {

    //User info we are trying to save to database.
    public String username;
    public String full_name;
    public String website;
    public String description;
    public String phone_number;

    /**
     * Constructor that doesn't initialize the class.
     */
    public UserInformation(){
    }

    /**
     * Saves the user information.
     *
     * @param username
     * @param full_name
     * @param website
     * @param description
     * @param phone_number
     */
    public UserInformation(String username, String full_name, String website, String description, String phone_number) {
        this.username = username;
        this.full_name = full_name;
        this.website = website;
        this.description = description;
        this.phone_number = phone_number;
    }
}
