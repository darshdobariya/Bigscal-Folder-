package com.example.pone;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

   /*What We do ?
    -- Develop UI for a Messaging application
    -- Implement an app using 3 Activities - Onboard, signIn and Home Activity
    -- On the onboard screen, show brief introduction to the app's features, such as messaging, voice and video calls, and file sharing.
    -- Show images, titles and subtitles to introduce app functionality.
    -- Add a button to check the next/previous features. Also, the skip button to skip the onboarding flow.
    -- On the sign-in screen, allow the user to enter their email and password, and add validation to ensure the user enters a valid email address and password.
    -- Use dummy email/password to verify user input.
    -- After a successful login, the user should be redirected to the Home screen.
    -- On the Home screen, show a list of chats with sender name, profile, latest message and message time.
    -- The user should not be able to go back to the login screen once redirected to the Home screen.
    -- Use dummy data for chats
    -- Add toggle button on Home screen to change the day/night theme
    -- App should responsive for different resolutions.
    -- Add support for day/night theme.
    -- You can use any images or placeholder to make UI eye-catchy
    -- App should follow material guidelines
    -- Here's UI for refrence

    over*/

public class HomeActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String DARK_MODE_KEY = "darkMode";
    private boolean darkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve dark mode preference from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        darkMode = prefs.getBoolean(DARK_MODE_KEY, false);
        updateDayNightMode();

        setContentView(R.layout.activity_home);

        FloatingActionButton fab = findViewById(R.id.fb);

        fab.setOnClickListener(v -> toggleDayNightMode());
    }

    private void toggleDayNightMode() {
        darkMode = !darkMode;

        // Save dark mode preference to SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(DARK_MODE_KEY, darkMode);
        editor.apply();

        updateDayNightMode();
    }

    private void updateDayNightMode() {
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
