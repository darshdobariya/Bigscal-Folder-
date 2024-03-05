package com.example.alltyperecycleview.nested;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.alltyperecycleview.R;
import com.example.alltyperecycleview.nested.adapter.MovieGroupAdapter;
import com.example.alltyperecycleview.nested.demo.Movie;
import com.example.alltyperecycleview.nested.demo.MovieGroup;

import java.util.ArrayList;
import java.util.List;

public class NestedActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieGroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested);

        recyclerView = findViewById(R.id.rcNestedParent);

        List<Movie> comedy= new ArrayList<>();
        comedy.add(new Movie("Hera Ferry", R.drawable.one, 10));
        comedy.add(new Movie("Golmal", R.drawable.two, 8));
        comedy.add(new Movie("SpiderMan", R.drawable.three, 8));
        comedy.add(new Movie("Flash", R.drawable.four, 5));
        comedy.add(new Movie("Pathan", R.drawable.five, 2));
        comedy.add(new Movie("Pathan", R.drawable.six, 10));
        comedy.add(new Movie("Pathan", R.drawable.seven, 9));
        comedy.add(new Movie("Pathan", R.drawable.eight, 6));

        List<Movie> action = new ArrayList<>();
        action.add(new Movie("Pathan", R.drawable.nine, 4));
        action.add(new Movie("War", R.drawable.ten, 7));
        action.add(new Movie("Sahoo", R.drawable.eleven, 9));
        action.add(new Movie("IronMan", R.drawable.twelve, 10));
        action.add(new Movie("Avengers", R.drawable.thirteen, 8));
        action.add(new Movie("Sultan", R.drawable.fourteen, 5));
        action.add(new Movie("Salaar", R.drawable.fifteen, 7));

        List<Movie> latest = new ArrayList<>();
        latest.add(new Movie("Pushpa", R.drawable.sixteen, 4));
        latest.add(new Movie("Animal", R.drawable.sixteen, 6));
        latest.add(new Movie("Aquaman2", R.drawable.seventeen, 6));
        latest.add(new Movie("Dangal", R.drawable.eighteen, 9));
        latest.add(new Movie("Wednesday", R.drawable.nineteen, 8));
        latest.add(new Movie("Tiger 3", R.drawable.twenty, 10));

        List<Movie> comedy1= new ArrayList<>();
        comedy1.add(new Movie("Hera Ferry", R.drawable.one, 10));
        comedy1.add(new Movie("Golmal", R.drawable.two, 8));
        comedy1.add(new Movie("SpiderMan", R.drawable.three, 8));
        comedy1.add(new Movie("Flash", R.drawable.four, 5));
        comedy1.add(new Movie("Pathan", R.drawable.five, 2));
        comedy1.add(new Movie("Pathan", R.drawable.six, 10));
        comedy1.add(new Movie("Pathan", R.drawable.seven, 9));
        comedy1.add(new Movie("Pathan", R.drawable.eight, 6));

        List<Movie> action1 = new ArrayList<>();
        action1.add(new Movie("Pathan", R.drawable.nine, 4));
        action1.add(new Movie("War", R.drawable.ten, 7));
        action1.add(new Movie("Sahoo", R.drawable.eleven, 9));
        action1.add(new Movie("IronMan", R.drawable.twelve, 10));
        action1.add(new Movie("Avengers", R.drawable.thirteen, 8));
        action1.add(new Movie("Sultan", R.drawable.fourteen, 5));
        action1.add(new Movie("Salaar", R.drawable.fifteen, 7));

        List<Movie> latest1 = new ArrayList<>();
        latest1.add(new Movie("Pushpa", R.drawable.sixteen, 4));
        latest1.add(new Movie("Animal", R.drawable.sixteen, 6));
        latest1.add(new Movie("Aquaman2", R.drawable.seventeen, 6));
        latest1.add(new Movie("Dangal", R.drawable.eighteen, 9));
        latest1.add(new Movie("Wednesday", R.drawable.nineteen, 8));
        latest1.add(new Movie("Tiger 3", R.drawable.twenty, 10));


        List<MovieGroup> movieGroupList = new ArrayList<>();
        movieGroupList.add(new MovieGroup("Comedy", comedy));
        movieGroupList.add(new MovieGroup("Action", action));
        movieGroupList.add(new MovieGroup("Latest", latest));
        movieGroupList.add(new MovieGroup("Comedy1", comedy1));
        movieGroupList.add(new MovieGroup("Action1", action1));
        movieGroupList.add(new MovieGroup("Latest1", latest1));

        adapter = new MovieGroupAdapter(this, movieGroupList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}