package com.example.test3.demo;

public class ChatDetail {
    private String Mobile;
    private String Name;
    private String Uid;

    public ChatDetail() {
    }

    public String getMobile() {
        return Mobile;
    }

    public String getName() {
        return Name;
    }

    public String getUid() {
        return Uid;
    }

    public ChatDetail(String mobile, String name, String uid) {
        Mobile = mobile;
        Name = name;
        Uid = uid;
    }
}
