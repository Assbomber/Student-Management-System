package com.example.studentmanagementsystem;

class Announcement {

    private String date;
    private String title;
    private String message;

    public Announcement(String Date,String Title,String Message){
        date=Date;
        title=Title;
        message=Message;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }
}
