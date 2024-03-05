package com.example.gfgbasics20.exampleone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.gfgbasics20.R;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<User> userList;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new CustomAdapter(this, userList);
        recyclerView.setAdapter(adapter);

        HomeViewModel viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.getMoviesListObserver(getApplicationContext()).observe(this, userModel -> {
            if(userModel != null) {
                userList = userModel;
                adapter.setMovieList(userModel);
            }
        });
        viewModel.makeApiCall();
    }
}