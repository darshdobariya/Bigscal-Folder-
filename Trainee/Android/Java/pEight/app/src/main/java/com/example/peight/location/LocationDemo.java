package com.example.peight.location;

public class LocationDemo {
    private String location;
    private int imgId;

    public LocationDemo(String location, int imgId) {
        this.location = location;
        this.imgId = imgId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
