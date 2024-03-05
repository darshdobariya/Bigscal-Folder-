package com.example.gfgbasics.layout.viewadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.gfgbasics.R;
import com.example.gfgbasics.layout.demo.GDDemo;
import com.example.gfgbasics.layout.demo.RCDemo;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {

    ArrayList<GDDemo> list;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gridView = findViewById(R.id.gridView);

        list = new ArrayList<>();

        list.add(new GDDemo("DSA Java", 5));
        list.add(new GDDemo("Java", 4));
        list.add(new GDDemo("C", 3));
        list.add(new GDDemo("Python", 5));
        list.add(new GDDemo("C#", 5));
        list.add(new GDDemo("Ruby", 2));
        list.add(new GDDemo("Pascal", 1));
        list.add(new GDDemo("SQL", 5));
        list.add(new GDDemo("HTML", 2));
        list.add(new GDDemo(".Net", 5));
        list.add(new GDDemo("DSA Java", 5));
        list.add(new GDDemo("Java", 4));
        list.add(new GDDemo("C", 3));
        list.add(new GDDemo("Python", 5));
        list.add(new GDDemo("C#", 5));
        list.add(new GDDemo("Ruby", 2));
        list.add(new GDDemo("Pascal", 1));
        list.add(new GDDemo("SQL", 5));
        list.add(new GDDemo("HTML", 2));
        list.add(new GDDemo(".Net", 5));

        GDAdapter adapter = new GDAdapter(this, list);
        gridView.setAdapter(adapter);
    }
}