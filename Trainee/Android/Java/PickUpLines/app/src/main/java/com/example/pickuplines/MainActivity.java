package com.example.pickuplines;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbarAndDrawer();

        loadDataInRC();
    }

    private void setToolbarAndDrawer(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.black));

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.remove_ads) {
            Intent i = new Intent(getApplicationContext(), RemoveAdsActivity.class);
            startActivity(i);
        }else if (item.getItemId() == R.id.favorite) {
            Intent i = new Intent(getApplicationContext(), FavoritesActivity.class);
            startActivity(i);
        }else if (item.getItemId() == R.id.pickup_line_of_the_day) {
            Intent i = new Intent(getApplicationContext(), PLoftheDayActivity.class);
            startActivity(i);
        }else if (item.getItemId() == R.id.pickup_line_maker) {
            Intent i = new Intent(getApplicationContext(), PLMakerAdsActivity.class);
            startActivity(i);
        }else if (item.getItemId() == R.id.setting) {
            Intent i = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(i);
        }else if (item.getItemId() == R.id.customer_support) {
            Intent i = new Intent(getApplicationContext(), CustomerSupportActivity.class);
            startActivity(i);
        }else if (item.getItemId() == R.id.privacy_policy) {
            /*Open privacy and policy page in web*/
        }else if (item.getItemId() == R.id.terms_and_condition) {
            /*Open terms and condition page in web*/
        }else if (item.getItemId() == R.id.rate_5_star) {
            /*Write a code to open dialog*/
        }else if (item.getItemId() == R.id.share_app) {
            /*Share this app with intent*/
        }
            return false;
    }

    private void loadDataInRC(){
        RecyclerView rcEmotion = findViewById(R.id.rcEmotion);

    }
}
