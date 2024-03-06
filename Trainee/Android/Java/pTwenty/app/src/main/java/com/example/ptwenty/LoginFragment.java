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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {

    private TextInputLayout tlMail, tlPassword;
    private TextInputEditText edtMail, edtPassword;
    private Button btnLogin;
    private TextView tvRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        tlMail = view.findViewById(R.id.tlMail);
        tlPassword = view.findViewById(R.id.tlPassword);
        edtMail = view.findViewById(R.id.edtMail);
        edtPassword = view.findViewById(R.id.edtPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvRegister = view.findViewById(R.id.tvRegister);

        tvRegister.setOnClickListener(v-> registerFragment());

        return view;
    }

    private void registerFragment(){
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction().replace(R.id.frmMain, new RegisterFragment()).addToBackStack(null);
        ft.commit();
    }
}