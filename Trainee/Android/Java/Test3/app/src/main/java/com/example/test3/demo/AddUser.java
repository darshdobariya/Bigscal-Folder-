package com.example.test3.demo;

public class AddUser {
    private String phone;
    private String uid;
    private String name;

    public AddUser(){

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddUser(String phone, String uid, String name) {
        this.phone = phone;
        this.uid = uid;
        this.name = name;
    }
}
