package com.example.shotatakada.project1;

public class trafficItems {
    private String mLabel;
    private String mImage;
    private String type;

    public trafficItems(String mLabel, String mImage, String type){
        this.mLabel = mLabel;
        this.type = type;
        if(type == "sdot"){
            this.mImage = "http://www.seattle.gov/trafficcams/images/" + mImage;
        }
        else{
            this.mImage="http://images.wsdot.wa.gov/nw/" + mImage;
        }


    }

    public String getLabel(){
        return mLabel;
    }

    public String getImage(){
        return mImage;
    }
}
