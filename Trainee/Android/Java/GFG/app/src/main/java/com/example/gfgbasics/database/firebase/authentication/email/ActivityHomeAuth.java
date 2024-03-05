package com.example.gfgbasics.database.firebase.authentication.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.gfgbasics.R;
import com.example.gfgbasics.database.firebase.authentication.google.GoogleAuthAct;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityHomeAuth extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_auth);

        Button logOut = findViewById(R.id.btnLogOut);
        logOut.setOnClickListener(v -> {
            if (mAuth.getCurrentUser() != null ) {
                mAuth.signOut();
               intentHome();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            intentHome();
        } else if (!currentUser.isEmailVerified()) {
            mAuth.signOut();
            intentHome();
        }
    }

    private void intentHome() {
        Intent intent = new Intent(getApplicationContext(), GoogleAuthAct.class);
        startActivity(intent);
        finish();
    }
}