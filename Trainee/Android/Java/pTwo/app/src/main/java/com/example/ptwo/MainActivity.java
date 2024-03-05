package com.example.ptwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

        /*What we do?
        -- Develop collapsing toolbar for the News application
        -- Home screen should show toolbar and news content
        -- The toolbar on screen should initially display the app's logo and title.
        -- As the user scrolls down to read news, the toolbar should collapse to provide more space for the content.
        -- You can use any dummy text/images as article content.
        -- When the user reaches the end of the news and reverses scrolls, the toolbar should re-expand and display the app's logo & title
        -- You can use any images or placeholder to make UI eyecatchy
        -- App should follow material guidelines
        -- Here's UI for reference*/

public class MainActivity extends AppCompatActivity {

    NewsAdapter adapter;
    RecyclerView rcNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcNews = findViewById(R.id.rcNews);

        List<Demo> list = new ArrayList<>();

        list.add(new Demo("its my first text in news", R.drawable.one));
        list.add(new Demo("its my Second text in news", R.drawable.two));
        list.add(new Demo("its my Third text in news", R.drawable.three));
        list.add(new Demo("its my Fourth text in news", R.drawable.volume));
        list.add(new Demo("its my Fifth text in news", R.drawable.volume));
        list.add(new Demo("its my Sixth text in news", R.drawable.one));
        list.add(new Demo("its my Seven text in news", R.drawable.three));
        list.add(new Demo("its my Eight text in news", R.drawable.volume));
        list.add(new Demo("its my nine text in news", R.drawable.one));
        list.add(new Demo("its my ten text in news", R.drawable.two));
        list.add(new Demo("its my 11 text in news", R.drawable.two));
        list.add(new Demo("its my 12 text in news", R.drawable.one));
        list.add(new Demo("its my 13 text in news", R.drawable.three));
        list.add(new Demo("its my 14 text in news", R.drawable.volume));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcNews.setLayoutManager(linearLayoutManager);

        adapter = new NewsAdapter(list);
        rcNews.setAdapter(adapter);
    }
}