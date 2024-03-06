package com.example.ptwenty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp = getSharedPreferences("MyData", MODE_PRIVATE);
        FragmentManager fm = getSupportFragmentManager();

        if (sp.getBoolean("token", false)){
            fm.beginTransaction().replace(R.id.frmMain, new HomeFragment()).addToBackStack(null).commit();
        }else{
            fm.beginTransaction().replace(R.id.frmMain, new LoginFragment()).addToBackStack(null).commit();
        }
    }
}