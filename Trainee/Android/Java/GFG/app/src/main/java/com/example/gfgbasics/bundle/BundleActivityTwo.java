package com.example.gfgbasics.bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gfgbasics.R;

public class BundleActivityTwo extends AppCompatActivity {

    TextView tvIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle_two);

        tvIntent = findViewById(R.id.tvIntent);

        Bundle i = getIntent().getExtras();

        tvIntent.setText(i.getString("Hello", "Activity 2 Not open yet"));

    }
}