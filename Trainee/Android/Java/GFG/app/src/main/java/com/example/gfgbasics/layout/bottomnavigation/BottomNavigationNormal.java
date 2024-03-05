package com.example.gfgbasics.layout.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.gfgbasics.R;
import com.example.gfgbasics.layout.bottomnavigation.normal.BNFOne;
import com.example.gfgbasics.layout.bottomnavigation.normal.BNFThree;
import com.example.gfgbasics.layout.bottomnavigation.normal.BNFTwo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationNormal extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_normal);

        loadFragment(new BNFOne());

        bottomNavigationView = findViewById(R.id.btmNavigation);
        frameLayout = findViewById(R.id.frameLayout);

        bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
    }

    public boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        if(item.getItemId() == R.id.home){
            fragment = new BNFOne();
        } else if (item.getItemId() == R.id.profile) {
            fragment = new BNFTwo();
        }else{
            fragment = new BNFThree();
        }

        return loadFragment(fragment);
    }
}