package com.example.androidtest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtest.R;

public class UserProfileActivity extends AppCompatActivity {

    TextView uName, uAge, uGender, uStudy, uAddress;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        uName = findViewById(R.id.tvName);
        uAge = findViewById(R.id.tvAge);
        uGender = findViewById(R.id.tvGender);
        uStudy = findViewById(R.id.tvStudy);
        uAddress = findViewById(R.id.tvAddress);
        imgBack = findViewById(R.id.imgBack);

        Intent intent = getIntent();

        uName.setText(String.valueOf(intent.getStringExtra("uName")));
        uAge.setText(String.valueOf(intent.getStringExtra("uAge")));
        uGender.setText(String.valueOf(intent.getStringExtra("uGender")));
        uStudy.setText(String.valueOf(intent.getStringExtra("uStudy")));
        uAddress.setText(String.valueOf(intent.getStringExtra("uAddress")));

        imgBack.setOnClickListener(v-> finish());
    }
}