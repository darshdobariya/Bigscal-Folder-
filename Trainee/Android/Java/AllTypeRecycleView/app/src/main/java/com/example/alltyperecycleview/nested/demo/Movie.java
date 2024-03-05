package com.example.alltyperecycleview.nested.demo;

public class Movie {
    private String name;
    private int image;
    private int IMDB;

    public Movie(String name, int image, int IMDB) {
        this.name = name;
        this.image = image;
        this.IMDB = IMDB;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getIMDB() {
        return IMDB;
    }
}
