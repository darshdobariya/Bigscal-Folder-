package com.example.gfgbasics.database.sharedpreferance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gfgbasics.R;

public class SharedPrefrenceActivity extends AppCompatActivity {

    Button btnInc, btnDec;
    EditText edtMsg;
    TextView tvCount;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefrence);

        btnInc = findViewById(R.id.btnInc);
        btnDec = findViewById(R.id.btnDec);
        edtMsg = findViewById(R.id.edtMsg);
        tvCount = findViewById(R.id.tvCount);

        btnInc.setOnClickListener(v->{
            count++;
            tvCount.setText(String.valueOf(count));
        });

        btnDec.setOnClickListener(v->{
            count--;
            tvCount.setText(String.valueOf(count));
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sp = getSharedPreferences("MyDataBase", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("count", count);
        editor.putString("msg", edtMsg.getText().toString());

        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sp = getSharedPreferences("MyDataBase", Context.MODE_PRIVATE);

        count = sp.getInt("count", 8);
        tvCount.setText(String.valueOf(count));
        edtMsg.setText(sp.getString("msg", ""));
    }
}