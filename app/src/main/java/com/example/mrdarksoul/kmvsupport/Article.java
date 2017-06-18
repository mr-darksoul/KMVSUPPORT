package com.example.mrdarksoul.kmvsupport;

/**
 * Created by Mr.Darksoul on 6/15/2017.
 */

public class Article {
    public String mTagline;
    public String mAuthor;
    public String mLongDesc;
    public String mShortDec;
    public String mContent;
    public String mDate;
    public String mDepartment;
    public String mURL;
    public Article()
    {

    }
    public Article(String mTagline, String mAuthor, String mLongDesc, String mShortDec, String mContent, String mDate, String mDepartment, String mURL) {
        this.mTagline = mTagline;
        this.mAuthor = mAuthor;
        this.mLongDesc = mLongDesc;
        this.mShortDec = mShortDec;
        this.mContent = mContent;
        this.mDate = mDate;
        this.mDepartment = mDepartment;
        this.mURL = mURL;
    }

    public String getmTagline() {
        return mTagline;
    }

    public void setmTagline(String mTagline) {
        this.mTagline = mTagline;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmLongDesc() {
        return mLongDesc;
    }

    public void setmLongDesc(String mLongDesc) {
        this.mLongDesc = mLongDesc;
    }

    public String getmShortDec() {
        return mShortDec;
    }

    public void setmShortDec(String mShortDec) {
        this.mShortDec = mShortDec;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmDepartment() {
        return mDepartment;
    }

    public void setmDepartment(String mDepartment) {
        this.mDepartment = mDepartment;
    }

    public String getmURL() {
        return mURL;
    }

    public void setmURL(String mURL) {
        this.mURL = mURL;
    }
}
