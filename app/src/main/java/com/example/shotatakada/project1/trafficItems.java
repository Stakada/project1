package com.example.shotatakada.project1;

public class trafficItems {
    private String mLabel;
    private String mImage;
    private String type;
    private Double lat;
    private Double lan;

    public trafficItems(String mLabel, String mImage, String type, Double lat, Double lan){
        this.mLabel = mLabel;
        this.type = type;
        if(this.type.equals("sdot")){
            this.mImage = "http://www.seattle.gov/trafficcams/images/" + mImage;
        }
        else{
            this.mImage="http://images.wsdot.wa.gov/nw/" + mImage;
        }
        this.lat = lat;
        this.lan = lan;
    }

    public Double getLat(){
        return lat;
    }
    public Double getLan(){
        return lan;
    }

    public String getLabel(){
        return mLabel + getLat() + getLan();
    }

    public String getImage(){
        return mImage;
    }
}
