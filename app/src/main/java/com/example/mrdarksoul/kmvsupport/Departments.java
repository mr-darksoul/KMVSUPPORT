package com.example.mrdarksoul.kmvsupport;

/**
 * Created by Mr.Darksoul on 6/19/2017.
 */

public class Departments {
    private String Incharge;
    private String Name;

    public Departments() {
    }

    public Departments(String incharge, String name) {
        Incharge = incharge;
        Name = name;
    }

    public String getIncharge() {
        return Incharge;
    }

    public void setIncharge(String incharge) {
        Incharge = incharge;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
