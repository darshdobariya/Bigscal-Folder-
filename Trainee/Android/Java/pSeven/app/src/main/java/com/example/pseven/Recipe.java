package com.example.pseven;

public class Recipe {
    private final int imgId;
    private final String item;
    private final String description;

    public Recipe(int imgId, String item, String description) {
        this.imgId = imgId;
        this.item = item;
        this.description = description;
    }

    public int getImgId() {
        return imgId;
    }

    public String getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }
}
