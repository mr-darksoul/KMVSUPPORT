package com.example.mrdarksoul.kmvsupport;

/**
 * Created by Mr.Darksoul on 6/21/2017.
 */

public class Members {
    private String uID;
    private int priority;

    public Members() {
    }

    public Members(String uID, int priority) {
        this.uID = uID;
        this.priority = priority;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
