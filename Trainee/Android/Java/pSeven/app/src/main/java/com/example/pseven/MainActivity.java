package com.example.pseven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    /*What we do?
    -- Develop RecipeLister application
    -- Single Activity app with two fragments - Home & detail fragment
    -- On the Home fragment add RecyclerView to display the list of recipes.
    -- Each list item should show the recipe name and a short description.
    -- Tapping a recipe should open Detail Fragment.
    -- On the Detail fragment show full recipe detail with recipe image and description
    -- Add a back button to navigate back to the list of recipes.
    -- Use dummy data for recipes*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new HomeFragment();
        fragmentManager.beginTransaction().add(R.id.frmLayout, fragment).addToBackStack(null).commit();
    }
}