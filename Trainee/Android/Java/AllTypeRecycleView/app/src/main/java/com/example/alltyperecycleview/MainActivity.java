package com.example.alltyperecycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.alltyperecycleview.multiview.MultiViewActivity;
import com.example.alltyperecycleview.nested.NestedActivity;

public class MainActivity extends AppCompatActivity {

    Button btnNested, btnMultiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNested = findViewById(R.id.btnNested);
        btnMultiView = findViewById(R.id.btnMultiView);

        btnMultiView.setOnClickListener(v->{
            Intent i = new Intent(this, MultiViewActivity.class);
            startActivity(i);
        });

        btnNested.setOnClickListener(v->{
            Intent i = new Intent(this, NestedActivity.class);
            startActivity(i);
        });
    }
}