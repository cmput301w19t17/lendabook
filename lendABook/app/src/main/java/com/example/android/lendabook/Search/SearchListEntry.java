package com.example.android.lendabook.Search;

public class SearchListEntry {
    //declare the attributes of the object
    private String name;
    private String description;
    private String owner;
    private String status;

    //Constructor for the object
    public SearchListEntry(String name, String description, String owner,String status){
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.status = status;
    }

    //Getters and setters
    public String getName(){return name;
    }

    public void setName(String name){this.name = name;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // To represent the object measurement as a string in the CardioList
    public String toString(){
        return this.name + "\n"+ this.description + "\n" + this.owner + "\n" + this.status;
    }
}