package com.example.gfgbasics.database.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Users implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

//    @ColumnInfo(email = "Email")
    private String email;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Users(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
