package com.example.gfgbasics.database.firebase.firestore.imageslider;

public class SliderData {

    private String imgUrl;

    public SliderData() {
    }

    public SliderData(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}