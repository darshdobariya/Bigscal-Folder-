package com.example.peight;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

public class SettingFragment extends Fragment {

    private ToggleButton tgTheme;
    private SharedPreferences sp;
    private boolean darkMode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        tgTheme = view.findViewById(R.id.tbTheme);
        tgTheme.setOnClickListener(v-> applyTheme());

        sp = requireActivity().getSharedPreferences("Theme", MODE_PRIVATE);
        darkMode = sp.getBoolean("theme", true);

        tgTheme.setChecked(darkMode);

        return view;
    }

    private void applyTheme(){
        SharedPreferences.Editor editor = sp.edit();

        darkMode = !darkMode;
        if (tgTheme.isChecked()){
            editor.putBoolean("theme", true);
        }else{
            editor.putBoolean("theme", false);
        }

        editor.putInt("count", 1);
        editor.apply();
        updateDayNightMode();
    }

    private void updateDayNightMode() {
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
}