package com.example.pnineteen;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private TextView tvDrinks, tvCategory, tvAlcoholic, tvInstruction, tvIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView searchView = findViewById(R.id.searchView);
        tvDrinks = findViewById(R.id.tvDrinks);
        tvCategory = findViewById(R.id.tvCategory);
        tvAlcoholic = findViewById(R.id.tvAlcoholic);
        tvInstruction = findViewById(R.id.tvInstruction);
        tvIngredients = findViewById(R.id.tvIngredients);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getDrinkDetails().observe(this, new Observer<List<DrinkDemo>>() {
            @Override
            public void onChanged(List<DrinkDemo> drinkDemos) {
                if (drinkDemos != null && !drinkDemos.isEmpty()) {
                    DrinkDemo drinkDemo = drinkDemos.get(0);
                    List<Drinks> drinksList = drinkDemo.getDrinks();
                    if (drinksList != null && !drinksList.isEmpty()) {
                        Drinks drinks = drinksList.get(0);
                        tvDrinks.setText(drinks.getStrDrink());
                        tvCategory.setText(drinks.getStrCategory());
                        tvAlcoholic.setText(drinks.getStrAlcoholic());
                        tvInstruction.setText(drinks.getStrInstructions());
                        // Set ingredients (concatenate non-empty ingredient fields)
                        StringBuilder ingredients = new StringBuilder();
                        for (int i = 1; i <= 15; i++) {
                            String ingredient = (String) drinks.getClass().getDeclaredFields()[i + 16].get(drinks);
                            if (ingredient != null && !ingredient.isEmpty()) {
                                ingredients.append(ingredient).append(", ");
                            }
                        }
                        tvIngredients.setText(ingredients.toString());
                    }
                } else {
                    Toast.makeText(MainActivity.this, "No drink found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform search when submit button is clicked
                viewModel.fetchDrinkDetails(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // You can perform search as the user types (optional)
                return false;
            }
        });
    }
}
