package com.example.pnineteen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDetails {
    @GET("search.php?")
    Call<List<DrinkDemo>> listRepos(@Query("s") String drink);
}