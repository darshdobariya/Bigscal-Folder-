package com.example.pickuplines.demo;

public class Emotion {

    private String emotion;
    private int imgId;

    public Emotion(String emotion, int imgId) {
        this.emotion = emotion;
        this.imgId = imgId;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
