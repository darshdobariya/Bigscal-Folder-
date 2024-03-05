package com.example.gfgbasics.database.firebase.authentication.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.gfgbasics.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class EmailAuthAct extends AppCompatActivity {

    Button btnLogin, btnSignUp, btnPassReset;
    TextInputEditText edtLogin, edtPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_auth);

        mAuth = FirebaseAuth.getInstance();

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtLogin = findViewById(R.id.edtLogin);
        edtPassword = findViewById(R.id.edtPassword);
        btnPassReset = findViewById(R.id.btnPassReset);

        btnPassReset.setOnClickListener(v-> resetPassword(String.valueOf(edtLogin.getText())));

        btnSignUp.setOnClickListener(v -> createUserAuthWithEmailAndPassword(String.valueOf(edtLogin.getText()), String.valueOf(edtPassword.getText())));

        btnLogin.setOnClickListener(v -> authSignInWithEmailAndPassword(String.valueOf(edtLogin.getText()), String.valueOf(edtPassword.getText())));
    }

    // Create User using email and password:
    protected final void createUserAuthWithEmailAndPassword(String emailId, String password) {
        mAuth.createUserWithEmailAndPassword(emailId, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "User created successfully.", Toast.LENGTH_LONG).show();
                verifyEmailIdSentEmail(Objects.requireNonNull(mAuth.getCurrentUser()));
            } else {
                Toast.makeText(getApplicationContext(), "User Creation failed...", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Auth User mail and password
    protected final void authSignInWithEmailAndPassword(String emailId, String password) {
        mAuth.signInWithEmailAndPassword(emailId, password).addOnCompleteListener(task -> {
            if (task.isSuccessful() && Objects.requireNonNull(mAuth.getCurrentUser()).isEmailVerified()) {
                Toast.makeText(getApplicationContext(), "Login successful.", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), ActivityHomeAuth.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Email Not verified or User Exist ... ", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Send Email link for verification
    protected final void verifyEmailIdSentEmail(FirebaseUser user) {
        user.sendEmailVerification()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Verification email sent successfully.", Toast.LENGTH_LONG).show();
                        if (checkIfEmailVerified(user)) {
                            startActivity(new Intent(getApplicationContext(), ActivityHomeAuth.class));
                            finish();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to send verification email.", Toast.LENGTH_LONG).show();
                    }
                }).addOnCanceledListener(() -> Toast.makeText(getApplicationContext(), "Sending verification email cancelled.", Toast.LENGTH_LONG).show());
    }

    // Verify User
    public static boolean checkIfEmailVerified(FirebaseUser user) {
        return user != null && user.isEmailVerified();
    }

    // Forgot Password
    protected final void resetPassword(String emailId) {
        mAuth.sendPasswordResetEmail(emailId)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Password reset email sent successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                }
            });
    }
}