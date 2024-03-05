package com.example.peighteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private MainModelView viewModel;
    private ThoughtsAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView thoughtRecyclerView = findViewById(R.id.rcView);
        EditText thoughtEditText = findViewById(R.id.edtThought);
        Button saveButton = findViewById(R.id.btnAdd);

        viewModel = new ViewModelProvider(this).get(MainModelView.class);

        adapter = new ThoughtsAdapter(viewModel.getThoughts());
        thoughtRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        thoughtRecyclerView.setAdapter(adapter);

        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football.");
        viewModel.addThought("How are you? all of you. Let's play football. How are you? all of you. Let's play football. How are you? all of you. Let's play football.");


        saveButton.setOnClickListener(v -> {
            String thought = Objects.requireNonNull(thoughtEditText.getText()).toString();
            if (!thought.isEmpty()) {
                viewModel.addThought(thought);
                adapter.notifyDataSetChanged();
                thoughtEditText.getText().clear();
            }
        });
    }
}