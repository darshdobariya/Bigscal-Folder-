package com.example.peight;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.peight.location.LocationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    /*What we do?
    -- Develop a Travel application
    -- Implement the app using one activity only
    -- Home screen should have a bottom bar with 3 tabs: Destinations, Search, and Settings.
    -- The Destinations tab should display a list of popular travel destinations with images and descriptions.
    -- The Search tab should Allow the user to search for destinations
    -- Add search view to search different destination
    -- Show a message in TextView to notify the user when the searched destination is not available
    -- Use dummy data for destinations
    -- The Settings tab should allow the user to customize app settings
    -- Add a toggle button for notification and day/night theme settings.
    -- App should preserve the state on tab change
    -- If the user scrolled to the bottom of the destinations screen, it should preserve the scroll state across the tab change.
    -- If a user searches for something on the search screen, the search result should be there when navigating to the search tab from other tabs.
    -- If the user changed the setting's toggle, it should stay as it is when the user navigate between the tabs
    */
    private Fragment frgLocation, frgSearch, frgSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);

        frgLocation = new LocationFragment();
        frgSearch = new SearchFragment();
        frgSetting = new SettingFragment();

        updateDayNightMode();

        bottomNavigationView.setOnItemSelectedListener(item -> {

            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.location){
                selectedFragment = frgLocation;
            }else if (item.getItemId() == R.id.search){
                selectedFragment = frgSearch;
            }else if (item.getItemId() == R.id.setting){
                selectedFragment = frgSetting;
            }

            if (selectedFragment != null) {
                replaceFragment(selectedFragment);
                return true;
            }

            return false;
        });
    }

    private void replaceFragment(Fragment selectedFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flFragment, selectedFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void updateDayNightMode() {
        boolean darkMode;

        SharedPreferences sp = getSharedPreferences("Theme", MODE_PRIVATE);
        darkMode = sp.getBoolean("theme", true);

        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        if (sp.getInt("count", 0) == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, frgSetting).commit();
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("count", 0);
            editor.apply();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, frgLocation).commit();
        }
    }
}
