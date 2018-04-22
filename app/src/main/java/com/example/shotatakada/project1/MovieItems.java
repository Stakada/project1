package com.example.shotatakada.project1;

public class MovieItems {
    private String mTitle;
    private String mYear;
    private String mImage;
    private String mDirector;
    private String mDesc;

    public MovieItems(String mTitle,
                      String mYear,
                      String mDirector,
                      String mImage,
                      String mDesc){
        this.mTitle = mTitle;
        this.mYear = mYear;
        this.mDirector = mDirector;
        this.mImage = mImage;
        this.mDesc = mDesc;

    }

    public String getTitle() {
        return mTitle;
    }

    public String getYear() {
        return mYear;
    }

    public String getImage() {
        return mImage;
    }

    public String getDesc() {
        return mDesc;
    }

    public String getDirector() {
        return mDirector;
    }

}
