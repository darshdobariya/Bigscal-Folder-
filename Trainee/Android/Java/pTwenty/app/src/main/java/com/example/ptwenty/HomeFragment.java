package com.example.ptwenty;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    private TextView tvInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvInfo = view.findViewById(R.id.tvInfo);
        Button btnLogout = view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(v-> btnLogoutClick());

        return view;
    }

    private void btnLogoutClick(){
        SharedPreferences sp = requireActivity().getSharedPreferences("MyData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putBoolean("token", false);
        editor.apply();

        FragmentManager fm = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction().replace(R.id.frmMain, new LoginFragment()).addToBackStack(null);
        ft.commit();
    }
}