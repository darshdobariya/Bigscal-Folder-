package com.example.gfgbasics.database.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.gfgbasics.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.auth.User;

public class RoomUpdateAct extends AppCompatActivity {
    TextInputEditText edtName, edtEmail;
    Button btnUpdate;
    private Users users;
    private UserDataBase dataBase;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_update);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtMail);
        btnUpdate = findViewById(R.id.btnUpdate);

        dataBase = UserDataBase.getINSTANCE(this);
        dao = dataBase.getDao();

        users = (Users) getIntent().getSerializableExtra("model");
        edtName.setText(users.getName());
        edtEmail.setText(users.getEmail());

        btnUpdate.setOnClickListener(v->{
            Users model = new Users(users.getId(), edtEmail.getText().toString(), edtName.getText().toString());
            dao.update(model);
            finish();
        });
    }
}