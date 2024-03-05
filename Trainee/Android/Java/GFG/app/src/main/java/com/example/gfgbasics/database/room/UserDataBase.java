package com.example.gfgbasics.database.room;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;

@Database(entities = {Users.class}, version = 2)
public abstract class UserDataBase extends RoomDatabase {

    // Did abstract because it's connected with database
    // if access only UserDao then we get null database
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
