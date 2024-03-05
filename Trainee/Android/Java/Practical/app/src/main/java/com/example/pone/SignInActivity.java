package com.example.pone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    private AppCompatEditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtEmail = findViewById(R.id.edtMail);
        edtPassword = findViewById(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v-> emailValidation(Objects.requireNonNull(edtEmail.getText()).toString(), Objects.requireNonNull(edtPassword.getText()).toString()));
    }

    private void emailValidation(String email, String password) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (email.equals("admin@mail.com") && password.equals("123")) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        } else {
            TextInputLayout tv = findViewById(R.id.tvEdit);
            tv.setError("Invalid email");
        }
    }
}