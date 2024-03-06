package com.example.ptwenty.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity
public class Users implements Serializable {

    @PrimaryKey
    @NotNull
    private String email;

    private String name;
    private String address;
    private String mobile;
    private String password;

    public Users(String name, String email, String mobile, String address, String password) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
