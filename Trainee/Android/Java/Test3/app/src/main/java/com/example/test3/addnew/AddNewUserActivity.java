package com.example.test3.addnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.test3.R;
import com.example.test3.adapter.AddNewAdapter;
import com.example.test3.demo.AddUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddNewUserActivity extends AppCompatActivity {

    AddNewAdapter addNewAdapter;
    DatabaseReference mRef;
    FirebaseDatabase database;
    List<AddUser> list;
    RecyclerView rcAddNew;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        rcAddNew = findViewById(R.id.rcAddNew);

        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Users");

        list = new ArrayList<>();

        addNewAdapter = new AddNewAdapter(this, list); // Initialize adapter

        rcAddNew.setLayoutManager(new LinearLayoutManager(this));
        rcAddNew.setAdapter(addNewAdapter); // Set adapter to RecyclerView

        loadData();
    }

    private void loadData() {
        list.clear();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        if (Objects.equals(snapshot1.getKey(), Objects.requireNonNull(mAuth.getCurrentUser()).getUid())) {
                            Log.e("user", "matched");
                        }else{
                            AddUser addUser = snapshot1.getValue(AddUser.class);
                            if (addUser != null) {
                                addNewAdapter.addUserData(snapshot1.child("Name").getValue(String.class),
                                        snapshot1.child("Mobile").getValue(String.class),
                                        snapshot1.child("Uid").getValue(String.class));
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}