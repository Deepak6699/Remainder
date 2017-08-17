package com.m.deepak.remainder;

/**
 * Created by DEEPAK on 8/16/2017.
 */

public class User {
    private String Title;
    private String Description;
    private String Date;


    public User(String cTitle,String cDescription,String cDate){
        Title=cTitle;
        Description=cDescription;
        Date=cDate;

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
