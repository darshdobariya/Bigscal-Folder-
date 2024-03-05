package com.example.gfgbasics.intent;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import com.example.gfgbasics.R;
import com.example.gfgbasics.layout.demo.CustomToast;

public class CustomToasts extends AppCompatActivity {

    Button btnToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_toasts);

        btnToast = findViewById(R.id.btnCustomToast);

        btnToast.setOnClickListener(v->{
            CustomToast.showCustomToast("Its a custom", this);
        });
    }
}