package com.example.testfour

import com.example.testfour.demo.WordData
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("entries/en/{word}")
    suspend fun getPosts(@Path("word") word: String): List<WordData>
}