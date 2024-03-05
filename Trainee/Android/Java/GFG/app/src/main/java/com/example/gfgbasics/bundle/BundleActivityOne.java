package com.example.gfgbasics.bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gfgbasics.R;

import java.util.List;

public class BundleActivityOne extends AppCompatActivity {

    Button btnClick, btnCounter;
    TextView tvCounter;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle_one);

        Uri uri = getIntent().getData();

        if (uri != null){
            List<String> list = uri.getPathSegments();
            String id = list.get(list.size()-1);
            Toast.makeText(this, "id" + id, Toast.LENGTH_SHORT).show();
        }

        btnClick = findViewById(R.id.btnClick);
        btnCounter = findViewById(R.id.btnClick2);
        tvCounter = findViewById(R.id.tvCounter);

        btnClick.setOnClickListener(v->{
            Intent i = new Intent(getApplicationContext(), BundleActivityTwo.class);
            Bundle b = new Bundle();
            b.putString("Hello0", "It's Activity 2");
            i.putExtras(b);
            startActivity(i);
        });

        btnCounter.setOnClickListener(v->{
            count += 1;
            tvCounter.setText(String.valueOf(count));
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("counter", count);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        count  = savedInstanceState.getInt("counter");
        tvCounter.setText(String.valueOf(count));

        super.onRestoreInstanceState(savedInstanceState);
    }
}