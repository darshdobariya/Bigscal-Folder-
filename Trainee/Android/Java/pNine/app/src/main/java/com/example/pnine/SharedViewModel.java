package com.example.pnine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> urlLiveData = new MutableLiveData<>();

    // Method to set the URL data
    public void setUrl(String url) {
        urlLiveData.setValue(url);
    }

    // Method to get the URL data as LiveData
    public LiveData<String> getUrl() {
        return urlLiveData;
    }
}