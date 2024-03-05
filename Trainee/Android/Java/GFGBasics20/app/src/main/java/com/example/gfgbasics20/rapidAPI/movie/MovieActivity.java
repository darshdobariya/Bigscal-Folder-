package com.example.gfgbasics20.rapidAPI.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.gfgbasics20.R;
import com.example.gfgbasics20.rapidAPI.movie.Demo.Movie;
import com.example.gfgbasics20.rapidAPI.movie.Demo.Results;
import com.example.gfgbasics20.rapidAPI.movie.adapter.MovieAdapter;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        fetchData();
    }

    public void fetchData() {

        progressDialog = createProgressDialog(this);

        String url = "https://ott-details.p.rapidapi.com/advancedsearch?start_year=1970&end_year=2023&min_imdb=6&max_imdb=7.8&genre=action&language=english&type=movie&sort=latest&page=1";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", "220d7f72ccmshfddea262702d887p112215jsn5cb634b5edbd")
                .addHeader("X-RapidAPI-Host", "ott-details.p.rapidapi.com")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                final String errorMessage = e.getMessage();
                Log.e("API Request", "Failed: " + errorMessage);
                runOnUiThread(() -> Toast.makeText(MovieActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String resp = response.body().string();
                        Gson gson = new Gson();
                        Movie movie = gson.fromJson(resp, Movie.class);
                        List<Results> results = movie.getResults();

                        runOnUiThread(() -> setListInAdapter(results));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void setListInAdapter(List<Results> results) {
        RecyclerView recyclerView = findViewById(R.id.rcMovie);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1); // Adjust the span count as needed
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter adapter = new MovieAdapter(this, results);
        adapter.loadData(results);
        recyclerView.setAdapter(adapter);

        progressDialog.dismiss();
    }

    public ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog dialog = new ProgressDialog(mContext);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_layout);
        return dialog;
    }
}
