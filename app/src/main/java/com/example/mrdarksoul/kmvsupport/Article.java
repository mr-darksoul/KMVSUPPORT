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
    public Article(String mTagline, String mAuthor, String mLongDesc, String mShortDesc, String mContent, String mDate, String mDepartment, String mImage) {
        this.Tagline = mTagline;
        this.Author = mAuthor;
        this.LongDesc = mLongDesc;
        this.ShortDesc = mShortDesc;
        this.Content = mContent;
        this.Date = mDate;
        this.Image = mImage;
        this.Department = mDepartment;
    }

    public String getTagline() {
        return Tagline;
    }

    public void setTagline(String mTagline) {
        this.Tagline = mTagline;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String mAuthor) {
        this.Author = mAuthor;
    }

    public String getLongDesc() {
        return LongDesc;
    }

    public void setLongDesc(String mLongDesc) {
        this.LongDesc = mLongDesc;
    }

    public String getShortDesc() {
        return ShortDesc;
    }

    public void setShortDesc(String mShortDec) {
        this.ShortDesc = mShortDec;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String mContent) {
        this.Content = mContent;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String mDate) {
        this.Date = mDate;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String mDepartment) {
        this.Department = mDepartment;
    }

    public String getImage() {
        return Image;
    }

    public void setURL(String mImage) {
        this.Image = mImage;
    }
}
