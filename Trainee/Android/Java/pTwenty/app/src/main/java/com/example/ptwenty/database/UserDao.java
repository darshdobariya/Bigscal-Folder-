package com.example.ptwenty.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void insert(Users users);

//    @Query("select * from Users where email = :email and password = :password")
//    void find(String email, String password);
}
