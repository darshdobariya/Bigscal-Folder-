package com.example.gfgbasics.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    // Here name is database name like abc.db, factory is null, version in like 1.0 and so on
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table User_Details(name TEXT primary key, contact TEXT, dateofbirth VARCHAR(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User_Details");
    }

    public boolean insertData(String name, String contact, String dateofbirth){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dateofbirth", dateofbirth);

        long result = db.insert("User_Details", null, contentValues);

        if(result == -1){
            return false;
        }else return true;
    }

    public boolean updateData(String name, String contact, String dateofbirth){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = db.rawQuery("select * from User_Details where name = ?", new String[]{name});
        if (cursor.getCount() > 0){
            contentValues.put("contact", contact);
            contentValues.put("dateofbirth", dateofbirth);

            long result = db.update("User_Details", contentValues, "name = ?", new String[]{name});

            if(result == -1){
                return false;
            }else return true;
        }else return false;
    }

    public boolean deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from User_Details where name = ?", new String[]{name});
        if (cursor.getCount() > 0){

            long result = db.delete("User_Details", "name = ?", new String[]{name});

            if(result == -1){
                return false;
            }else return true;
        }else return false;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from User_Details", null);
        return cursor;
    }
}
