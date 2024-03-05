package com.example.gfgbasics.advanceview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gfgbasics.R;
import com.github.zagum.switchicon.SwitchIconView;

public class CustomSwitcher extends AppCompatActivity {

    SwitchIconView btnSwiitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_switcher);

        btnSwiitch = findViewById(R.id.switchIconView2);

        btnSwiitch.setOnClickListener(v->{
            btnSwiitch.switchState();
        });
    }
}