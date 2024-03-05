package com.example.gfgbasics.intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gfgbasics.R;

import es.dmoral.toasty.Toasty;

public class Toastys extends AppCompatActivity {

    Button button_error, button_success, button_info, button_warning, button_normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toastys);

        button_error = findViewById(R.id.button_error);
        button_success = findViewById(R.id.button_success);
        button_info = findViewById(R.id.button_info);
        button_warning = findViewById(R.id.button_warning);
        button_normal = findViewById(R.id.button_normal);

        button_error.setOnClickListener(v->{
            Toasty.error(this, "This is an error Toast", Toast.LENGTH_SHORT).show();
        });

        button_success.setOnClickListener(v->{
            Toasty.success(this, "This is an error Toast", Toast.LENGTH_SHORT).show();
        });

        button_info.setOnClickListener(v->{
            Toasty.info(this, "This is an error Toast", Toast.LENGTH_SHORT).show();
        });

        button_warning.setOnClickListener(v->{
            Toasty.warning(this, "This is an error Toast", Toast.LENGTH_SHORT).show();
        });

        button_normal.setOnClickListener(v->{
            Toasty.normal(this, "This is an error Toast", ContextCompat.getDrawable(this, R.drawable.home)).show();
        });
    }
}