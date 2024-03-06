package com.example.ptwenty.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class}, version = 1)
public abstract class UserDataBase extends RoomDatabase {
    public abstract UserDao getDao();

    public static UserDataBase INSTANCE;

    public static synchronized UserDataBase getINSTANCE(Context context) {
        if (INSTANCE == null && context != null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDataBase.class, "UserDataBase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}