package com.example.pnineteen;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<DrinkDemo>> drinkDetails = new MutableLiveData<>();
    private final GetDetails getDetails;

    public LiveData<List<DrinkDemo>> getDrinkDetails() {
        return drinkDetails;
    }

    public MainViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getDetails = retrofit.create(GetDetails.class);
    }

    public void fetchDrinkDetails(String drink) {
        Call<List<DrinkDemo>> call = getDetails.listRepos(drink);
        Log.e("Request URL", call.request().url().toString()); // Print the request URL

        call.enqueue(new Callback<List<DrinkDemo>>() {
            @Override
            public void onResponse(@NonNull Call<List<DrinkDemo>> call, @NonNull Response<List<DrinkDemo>> response) {
                if (response.isSuccessful()) {
                    drinkDetails.setValue(response.body());
                    assert response.body() != null;
                    Log.e("Response got : ", response.body().toString());
                } else {
                    // Handle unsuccessful response
                    Log.e("Response fail :", "response sent to fail");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DrinkDemo>> call, @NonNull Throwable t) {
                // Handle failure
                Log.e("Response failed like this : ", "Failed to retrieve response" + t);
            }
        });
    }
}
