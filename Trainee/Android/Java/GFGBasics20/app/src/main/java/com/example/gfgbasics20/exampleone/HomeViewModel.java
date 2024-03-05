package com.example.gfgbasics20.exampleone;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<User>> userList;
    private Context context;

    public MutableLiveData<List<User>> getMoviesListObserver(Context context) {
        this.context = context;
        return userList;
    }

    public HomeViewModel(){
        userList = new MutableLiveData<>();
    }

    public void makeApiCall() {
        RetrofitInterface apiService = RetrofitClient.getClient().create(RetrofitInterface.class);
        Call<List<User>> call = apiService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                userList.postValue(response.body());

                Toast.makeText(context,"user.getId()",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                userList.postValue(null);
            }
        });
    }
}
