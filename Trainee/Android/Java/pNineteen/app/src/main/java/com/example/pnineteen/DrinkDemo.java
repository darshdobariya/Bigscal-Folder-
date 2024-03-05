package com.example.pnineteen;

import java.util.List;

import com.google.gson.annotations.SerializedName;
   
public class DrinkDemo {

   @SerializedName("drinks")
   List<Drinks> drinks;

    public void setDrinks(List<Drinks> drinks) {
        this.drinks = drinks;
    }
    public List<Drinks> getDrinks() {
        return drinks;
    }
    
}