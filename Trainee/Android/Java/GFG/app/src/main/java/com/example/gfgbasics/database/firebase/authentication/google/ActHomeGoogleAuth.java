package com.example.gfgbasics.database.firebase.authentication.google;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.gfgbasics.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ActHomeGoogleAuth extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_home_google_auth);

        Button btnSignOut = findViewById(R.id.btnLogOut);
        Button btnShow = findViewById(R.id.btnShow);

        btnShow.setOnClickListener(v->{
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            if (account != null) {
                String email = account.getEmail();
                Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
            }
        });

        btnSignOut.setOnClickListener(v->{
            if (GoogleSignIn.getLastSignedInAccount(this) == null){
                Intent i = new Intent(getApplicationContext(), GoogleAuthAct.class);
                startActivity(i);
                finish();
            }else {
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent i = new Intent(getApplicationContext(), GoogleAuthAct.class);
                                startActivity(i);
                                finish();
                            }
                        });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check if the user is already signed in
        if (GoogleSignIn.getLastSignedInAccount(this) == null) {
            // If not signed in, redirect to GoogleAuthAct
            Intent i = new Intent(getApplicationContext(), GoogleAuthAct.class);
            startActivity(i);
            finish();
        }
    }
}