package com.example.mrdarksoul.kmvsupport;

/**
 * Created by Mr.Darksoul on 6/15/2017.
 */

public class Article {
    private String Tagline;
    private String Author;
    private String LongDesc;
    private String ShortDesc;
    private String Content;
    private String Date;
    private String Department;
    private String Image;
    public Article()
    {

    }
    public Article(String mTagline, String mAuthor, String mLongDesc, String mShortDec, String mContent, String mDate, String mDepartment, String mImage) {
        this.Tagline = mTagline;
        this.Author = mAuthor;
        this.LongDesc = mLongDesc;
        this.ShortDesc = mShortDec;
        this.Content = mContent;
        this.Date = mDate;
        this.Image = mImage;
        this.Department = mDepartment;
    }

    public String getmTagline() {
        return Tagline;
    }

    public void setmTagline(String mTagline) {
        this.Tagline = mTagline;
    }

    public String getmAuthor() {
        return Author;
    }

    public void setmAuthor(String mAuthor) {
        this.Author = mAuthor;
    }

    public String getmLongDesc() {
        return LongDesc;
    }

    public void setmLongDesc(String mLongDesc) {
        this.LongDesc = mLongDesc;
    }

    public String getmShortDec() {
        return ShortDesc;
    }

    public void setmShortDec(String mShortDec) {
        this.ShortDesc = mShortDec;
    }

    public String getmContent() {
        return Content;
    }

    public void setmContent(String mContent) {
        this.Content = mContent;
    }

    public String getmDate() {
        return Date;
    }

    public void setmDate(String mDate) {
        this.Date = mDate;
    }

    public String getmDepartment() {
        return Department;
    }

    public void setmDepartment(String mDepartment) {
        this.Department = mDepartment;
    }

    public String getmImage() {
        return Image;
    }

    public void setmURL(String mImage) {
        this.Image = mImage;
    }
}
