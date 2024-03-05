package com.example.gfgbasics.architecture.mvp;

import androidx.annotation.NonNull;

public class MVPDemo {
    private String userName = "";
    private String mail = "";
    public MVPDemo(){

    }

    public MVPDemo(String userName, String mail) {
        this.userName = userName;
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NonNull
    @Override
    public String toString(){
        return "UserName : " + getUserName() + "\n" + "Email : " + getMail();
    }
}