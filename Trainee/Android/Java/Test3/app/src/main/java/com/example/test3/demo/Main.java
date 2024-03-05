package com.example.test3.demo;

import java.io.Serializable;

public class Main implements Serializable {
    private String name;
    private String time;
    private String count;
    private String uid;

    public Main(){

    }

    public Main(String name, String time, String count, String uid) {
        this.name = name;
        this.time = time;
        this.count = count;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
