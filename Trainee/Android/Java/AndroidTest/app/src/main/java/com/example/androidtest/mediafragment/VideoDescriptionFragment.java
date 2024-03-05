package com.example.androidtest.mediafragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidtest.R;

public class VideoDescriptionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_description, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("888888888888", " Frag 2 start");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("888888888888", " Frag 2 pause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("888888888888", " Frag 2 resume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("888888888888", " Frag 2 stop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("888888888888", " Frag 2 destroy");
    }
}