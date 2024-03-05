package com.example.test3.afterlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.test3.MainActivity;
import com.example.test3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AfterLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        AppCompatEditText edtName = findViewById(R.id.edtName);
        Button bntNext = findViewById(R.id.btnNext);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference("Users");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        bntNext.setOnClickListener(v->{

            if (Objects.requireNonNull(edtName.getText()).toString().trim().length() < 2){
                Toast.makeText(this, "name length should be more than 2", Toast.LENGTH_SHORT).show();
            }else{

                mRef.child(Objects.requireNonNull(mAuth.getCurrentUser()).getUid()).child("Name").setValue(edtName.getText().toString().trim());

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}