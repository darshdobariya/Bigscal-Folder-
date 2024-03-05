package com.example.pnine;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

public class BrowserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browser, container, false);
        TextView tvBack = view.findViewById(R.id.tvBack);
        WebView webView = view.findViewById(R.id.webView);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        webView.loadUrl(sharedPreferences.getString("url", ""));

        tvBack.setOnClickListener(v-> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }
}