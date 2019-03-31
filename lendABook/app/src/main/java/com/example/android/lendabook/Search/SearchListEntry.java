package com.example.android.lendabook.Search;

/**
 * Created by oleg on 2019-03-26.
 * Class for storing list entries in search results
 */

/**
 * This is a class to store data for entries in a list
 */

public class SearchListEntry {
    //declare the attributes of the object
    private String name;
    private String description;
    private String owner;
    private String status;

    /**
     * Creates object with info.
     *
     * @param name
     * @param description
     * @param owner
     * @param status
     */
    public SearchListEntry(String name, String description, String owner,String status){
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.status = status;
    }

    //Getters and setters
    public String getName(){return name;
    }

    /**
     * name setter 
     *
     * @param name
     */
    public void setName(String name){this.name = name;}

    public String getDescription() {
        return description;
    }
    
    /**
     * description setter 
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }
    
    /**
     * owner setter 
     *
     * @param owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }
    
    /**
     * status setter 
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    // To represent the object measurement as a string in the CardioList
    public String toString(){
        return this.name + "\n"+ this.description + "\n" + this.owner + "\n" + this.status;
    }
}
