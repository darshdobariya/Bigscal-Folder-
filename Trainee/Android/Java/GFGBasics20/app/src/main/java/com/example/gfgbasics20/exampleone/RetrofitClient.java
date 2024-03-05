package com.example.gfgbasics20.exampleone;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    /*https://jsonkeeper.com/b/*/

    public static Retrofit getClient() {

        if (retrofit==null) {
            String BASE_URL = "https://jsonplaceholder.typicode.com/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
