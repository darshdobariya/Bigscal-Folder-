package com.example.test3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.test3.adapter.AdapterMain;
import com.example.test3.addnew.AddNewUserActivity;
import com.example.test3.demo.Main;
import com.example.test3.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    AdapterMain adapterMain;
    DatabaseReference mRef;
    FirebaseDatabase database;
    List<Main> list;
    RecyclerView rcMain;
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbarMain);
        rcMain = findViewById(R.id.rcMain);
        btnAdd = findViewById(R.id.btnAdd);
        SwipeRefreshLayout swipeMain = findViewById(R.id.swipeMain);
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Users");

        list = new ArrayList<>();

        adapterMain = new AdapterMain(this, list);

        rcMain.setLayoutManager(new LinearLayoutManager(this));
        rcMain.setAdapter(adapterMain);

        btnAdd.setOnClickListener(v->{
            Intent i = new Intent(getApplicationContext(), AddNewUserActivity.class);
            startActivity(i);
        });

        swipeMain.setOnRefreshListener(() -> {
            loadData();
            swipeMain.setRefreshing(false);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null){
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData(){
        list.clear(); // Clear list before adding data
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                        Toast.makeText(MainActivity.this, snapshot1.getKey(), Toast.LENGTH_SHORT).show();
                        if (Objects.equals(snapshot1.getKey(), Objects.requireNonNull(mAuth.getCurrentUser()).getUid())) {
                            DataSnapshot chatSnapshot = snapshot1.child("Chat");
                            if (chatSnapshot.exists()) {
                                for (DataSnapshot snapshot2 : chatSnapshot.getChildren()) {
                                    if (snapshot2 != null) {
                                        adapterMain.addData(snapshot2.child("Name").getValue(String.class),
                                                "Zero",
                                                "Null",
                                                snapshot2.child("Uid").getValue(String.class));
                                    }
                                }
                            }
                            SharedPreferences sharedpreferences;

                            sharedpreferences = getSharedPreferences("mypreference", Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("Name", snapshot1.child("Name").getValue(String.class));
                            editor.apply();
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
