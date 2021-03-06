package com.example.pluscomputers.publictoilet2;

public class ListLocation {

    private String img;
    private String name;
    private String type;
    private String describtion;
    private String distance;
    private double mLatitude;
    private double mLongitude;

    public ListLocation(String img, String name, String tipi, String describtion, String distanca, double latitude, double longitude) {
        this.img = img;
        this.name = name;
        this.type = tipi;
        this.describtion = describtion;
        this.distance = distanca;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }


    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public String getDescribtion() {
        return describtion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getDistance(){
        return distance;
    }

    public void setDistance(String distance){
        this.distance = distance;
    }

    public double getmLatitude(){
        return mLatitude;
    }

    public void setmLatitude(long distance){
        mLatitude = distance;
    }

    public double getmLongitude(){
        return mLongitude;
    }

    public void setmLongitude(long distance){
        mLongitude = distance;
    }
}