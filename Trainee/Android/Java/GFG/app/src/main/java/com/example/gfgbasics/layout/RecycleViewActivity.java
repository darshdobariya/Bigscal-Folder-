package com.example.gfgbasics.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.gfgbasics.R;
import com.example.gfgbasics.layout.demo.RCDemo;
import com.example.gfgbasics.layout.viewadapter.RCAdapter;

import java.util.ArrayList;

public class RecycleViewActivity extends AppCompatActivity {

    RCAdapter adapter;
    RCDemo rcDemo;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        recyclerView = findViewById(R.id.rcView);

        ArrayList<RCDemo> list = new ArrayList<>();
        list.add(new RCDemo("DSA Java", 5));
        list.add(new RCDemo("Java", 4));
        list.add(new RCDemo("C", 3));
        list.add(new RCDemo("Python", 5));
        list.add(new RCDemo("C#", 5));
        list.add(new RCDemo("Ruby", 2));
        list.add(new RCDemo("Pascal", 1));
        list.add(new RCDemo("SQL", 5));
        list.add(new RCDemo("HTML", 2));
        list.add(new RCDemo(".Net", 5));

        adapter = new RCAdapter(this, list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}