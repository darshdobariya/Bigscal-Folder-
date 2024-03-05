package com.example.gfgbasics.database.room;

public interface AdapterListner {

    void OnUpdate(Users user);
    void OnDelete(int id, int pos);
}
