package com.example.ptwenty;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ptwenty.database.UserDao;
import com.example.ptwenty.database.UserDataBase;
import com.example.ptwenty.database.Users;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private TextInputLayout tlName, tlEmail, tlMobile, tlPassword;
    private TextInputEditText edtName, edtEmail, edtMobile, edtAddress, edtPassword;
    private UserDao dao;
    private UserDataBase userDataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        tlName = view.findViewById(R.id.tlName);
        tlEmail = view.findViewById(R.id.tlEmail);
        tlMobile = view.findViewById(R.id.tlMobile);
        tlPassword = view.findViewById(R.id.tlPassword);
        edtName = view.findViewById(R.id.edtName);
        edtEmail = view.findViewById(R.id.edtMail);
        edtMobile = view.findViewById(R.id.edtMobile);
        edtAddress = view.findViewById(R.id.edtPassword);
        edtPassword = view.findViewById(R.id.edtAddress);
        Button btnRegister = view.findViewById(R.id.btnRegister);
        TextView tvLogin = view.findViewById(R.id.tvLogin);

        tvLogin.setOnClickListener(v-> loginFragment());

        btnRegister.setOnClickListener(v-> registerClick(Objects.requireNonNull(edtName.getText()).toString(), Objects.requireNonNull(edtEmail.getText()).toString(), Objects.requireNonNull(edtMobile.getText()).toString(), Objects.requireNonNull(edtAddress.getText()).toString(), Objects.requireNonNull(edtPassword.getText()).toString()));

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tlEmail.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tlMobile.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tlPassword.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tlName.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    private void loginFragment(){
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction().replace(R.id.frmMain, new LoginFragment()).addToBackStack(null);
        ft.commit();
    }

    private void registerClick(String name, String email, String mobile, String address, String password){
        if (validUser(name, email, mobile, password)){

            userDataBase = UserDataBase.getINSTANCE(requireActivity());
            dao = userDataBase.getDao();

            Users model = new Users(name, email, mobile, address, password);
            dao.insert(model);

            loginFragment();
        }
    }

    private boolean validUser(String name, String email, String mobile, String password){
        if (validEmail(email)){
            if (mobile.length() == 10){
                if (password.length() >= 8){
                    if (name.length() > 1){
                        return true;
                    }else tlName.setError("Name is too short");
                }else tlPassword.setError("Password is too short");
            }else tlMobile.setError("Invalid Mobile");
        }else tlEmail.setError("Invalid email");

        return false;
    }

    private boolean validEmail(String email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        return email.matches(emailPattern) && email.length() > 0;
    }
}