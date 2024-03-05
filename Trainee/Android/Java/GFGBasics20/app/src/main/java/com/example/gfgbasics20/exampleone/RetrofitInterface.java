package com.example.gfgbasics20.exampleone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("posts")
    Call<List<User>> getUsers();

    /*T4BG*/
}
