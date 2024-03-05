package com.example.alltyperecycleview.multiview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Adapter;

import com.example.alltyperecycleview.R;
import com.example.alltyperecycleview.multiview.adapter.AdapterMultiView;
import com.example.alltyperecycleview.multiview.demo.MultiView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MultiViewActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    List<MultiView> itemClasses = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_view);
        recyclerView = findViewById(R.id.rcMultiViewActivity);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);

        // Create and set the layout manager
        // For the RecyclerView.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // pass the arguments
        itemClasses.add(new MultiView(MultiView.LayoutOne, "Item Type 1"));
        itemClasses.add(new MultiView(MultiView.LayoutOne, "Item Type 2"));
        itemClasses.add(new MultiView(MultiView.LayoutTwo, R.drawable.home_work, "Item Type 11", "Text"));
        itemClasses.add(new MultiView(MultiView.LayoutOne,"Item Type 3"));
        itemClasses.add(new MultiView(MultiView.LayoutOne, "Item Type 4"));
        itemClasses.add(new MultiView(MultiView.LayoutTwo, R.drawable.home_work, "Item Type 12", "Text"));
        itemClasses.add(new MultiView(MultiView.LayoutOne, "Item Type 5"));
        itemClasses.add(new MultiView(MultiView.LayoutOne, "Item Type 6"));
        itemClasses.add(new MultiView(MultiView.LayoutTwo, R.drawable.home_work, "Item Type 9", "Text"));
        itemClasses.add(new MultiView(MultiView.LayoutOne, "Item Type 7"));
        itemClasses.add(new MultiView(MultiView.LayoutOne, "Item Type 8"));
        itemClasses.add(new MultiView(MultiView.LayoutTwo, R.drawable.home_work, "Item Type 10", "Text"));

        AdapterMultiView adapter = new AdapterMultiView(itemClasses);

        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                RearrangeItems();
            }
        });
    }

    public void RearrangeItems() {
        Collections.shuffle(itemClasses, new Random(System.currentTimeMillis()));
        AdapterMultiView adapter = new AdapterMultiView(itemClasses);
        recyclerView.setAdapter(adapter);
    }
}