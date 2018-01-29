package com.example.pluscomputers.publictoilet2;

public class ListLocation {

    private int img;
    private String name;
    private String type;
    private String distance;
    private double mLatitude;
    private double mLongitude;

    public ListLocation(int img, String name, String tipi, String distanca, double latitude, double longitude) {
        this.img = img;
        this.name = name;
        this.type = tipi;
        this.distance = distanca;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
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