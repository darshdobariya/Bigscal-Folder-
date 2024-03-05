package com.example.peleven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class Reciever extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever);

        AppCompatEditText edtReciever = findViewById(R.id.edtReciever);
        Button btnReciever = findViewById(R.id.btnReciever);
        TextView tvReciever = findViewById(R.id.tvReciever);

        btnReciever.setOnClickListener(v->{
            Intent resultIntent = new Intent();
            resultIntent.putExtra("Text", Objects.requireNonNull(edtReciever.getText()).toString());
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });

        Intent i = getIntent();
        String msg = "Received from sender activity : " + i.getStringExtra("Text");
        tvReciever.setText(msg);
    }
}