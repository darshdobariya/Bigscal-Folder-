package com.example.test3.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.test3.MainActivity;
import com.example.test3.R;
import com.example.test3.afterlogin.AfterLoginActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    AppCompatEditText edtMobile, edtOtp;
    TextInputLayout tvOtp;
    Button btnLogin;
    private FirebaseAuth mAuth;
    private String verificationId;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtMobile = findViewById(R.id.edtMobile);
        edtOtp = findViewById(R.id.edtOtp);
        btnLogin = findViewById(R.id.btnLogin);
        tvOtp = findViewById(R.id.tvOtp);

        btnLogin.setEnabled(false);
        mAuth = FirebaseAuth.getInstance();

        tvOtp.setEndIconOnClickListener(v -> {
            if (TextUtils.isEmpty(Objects.requireNonNull(edtMobile.getText()).toString()) && edtMobile.getText().length() != 10) {
                Toast.makeText(getApplicationContext(), "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
            } else {
                if (mAuth == null) {
                    mAuth = FirebaseAuth.getInstance();
                }
                String phone = "+91" + edtMobile.getText().toString();
                sendVerificationCode(phone);
                tvOtp.setEndIconMode(TextInputLayout.END_ICON_NONE);
            }
        });

        btnLogin.setOnClickListener(v->{
            // validating if the OTP text field is empty or not.
            if (TextUtils.isEmpty(Objects.requireNonNull(edtOtp.getText()).toString())) {
                // if the OTP text field is empty display
                // a message to user to enter OTP
                Toast.makeText(getApplicationContext(), "Please enter OTP", Toast.LENGTH_SHORT).show();
            } else {
                // if OTP field is not empty calling
                // method to verify the OTP.
                verifyCode(edtOtp.getText().toString());
            }
        });
    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        loginSuccessful();
                    } else {
                        Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void loginSuccessful() {

        String uid = mAuth.getCurrentUser().getUid();
        String mobile = String.valueOf(mAuth.getCurrentUser().getPhoneNumber());

        final boolean[] FLAG = {false};

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference("Users");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ignored : snapshot.getChildren()){
                        if (Objects.equals(snapshot.getKey(), uid)){
                            FLAG[0] = true;
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (!FLAG[0]){
            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        for (DataSnapshot snapshot1 : snapshot.getChildren()){
                            if (mAuth.getCurrentUser().getUid().equals(snapshot1.getKey())){
                                count = 1;
                                break;
                            }
                        }

                        if (count == 1){
                            startNewActivity();
                        }else{
                            mRef.child(mAuth.getCurrentUser().getUid()).child("Mobile").setValue(mobile);
                            mRef.child(uid).child("Uid").setValue(uid);
                            mRef.child(uid).child("ProfileImage").setValue("null");

                            Intent i = new Intent(getApplicationContext(), AfterLoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void sendVerificationCode(String number) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks


            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            btnLogin.setEnabled(true);
            Toast.makeText(LoginActivity.this, "Code Sent", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            final String code = phoneAuthCredential.getSmsCode();

            if (edtOtp != null && code != null) {
                edtOtp.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void startNewActivity(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}