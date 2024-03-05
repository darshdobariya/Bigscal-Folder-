package com.example.gfgbasics.architecture.mvp.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gfgbasics.R;

import java.util.Objects;

public class LoginMVPActivity extends AppCompatActivity implements LoginContract.View{

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mvpactivity);

        AppCompatEditText edtEmail = findViewById(R.id.edtEmail);
        AppCompatEditText edtPass = findViewById(R.id.edtPassword);
        AppCompatButton btnLogin = findViewById(R.id.loginButton);
        presenter = new LoginPresenter(this);

        btnLogin.setOnClickListener(v->{
            presenter.onLoginButtonClick(Objects.requireNonNull(edtEmail.getText()).toString().trim(), Objects.requireNonNull(edtPass.getText()).toString().trim());
        });

    }

    @Override
    public void showInvalidCredentialError() {
        Toast.makeText(getApplicationContext(), "Invalid Credential", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginSuccessMessage() {
        Intent i = new Intent(getApplicationContext(), HomeMVPActivity.class);
        startActivity(i);

         /* Bungee.zoom(getApplicationContext());
            Bungee.split(getApplicationContext());
            Bungee.shrink(getApplicationContext());
            Bungee.card(getApplicationContext());
            Bungee.inAndOut(getApplicationContext());
            Bungee.swipeLeft(getApplicationContext());
            Bungee.swiperRight(getApplicationContext());
            Bungee.slideLeft(getApplicationContext());
            Bungee.slideRight(getApplicationContext());
            Bungee.slideDown(getApplicationContext());
            Bungee.slideUp(getApplicationContext());
            Bungee.fade(getApplicationContext());
            Bungee.windmill(getApplicationContext());
            Bungee.diagonal(getApplicationContext());
            Bungee.spin(getApplicationContext());
          */
    }
}