package com.example.androidtest.demo;

public class User {

    private final String uNAme;
    private final int uAge;
    private final String uGender;
    private final String uStudy;
    private final String uAddress;
    public User(String uNAme, int uAge, String uGender, String uStudy, String uAddress) {
        this.uNAme = uNAme;
        this.uAge = uAge;
        this.uGender = uGender;
        this.uStudy = uStudy;
        this.uAddress = uAddress;
    }

    public String getuNAme() {
        return uNAme;
    }

    public int getuAge() {
        return uAge;
    }

    public String getuGender() {
        return uGender;
    }

    public String getuStudy() {
        return uStudy;
    }

    public String getuAddress() {
        return uAddress;
    }
}
