package com.example.gfgbasics20.rapidAPI.movie.Demo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

    @SerializedName("page")
    int page;

    @SerializedName("results")
    List<Results> results;

    public void setPage(int page) {
        this.page = page;
    }
    public int getPage() {
        return page;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
    public List<Results> getResults() {
        return results;
    }
}