package com.example.gfgbasics.database.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.gfgbasics.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class RoomDBAct extends AppCompatActivity implements AdapterListner{

    RoomDBAdapter adapter;
    TextInputEditText edtName, edtEmail;
    Button btnInsert;
    private UserDataBase userDataBase;
    private UserDao userDao;
    RecyclerView rcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_dbact);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtMail);
        btnInsert = findViewById(R.id.btnInsert);
        rcView = findViewById(R.id.rcRoom);

        userDataBase = UserDataBase.getINSTANCE(this);
        userDao = userDataBase.getDao();

        btnInsert.setOnClickListener(v->{
            Users users = new Users(0, edtEmail.getText().toString(), edtName.getText().toString());
            userDao.insert(users);

            edtName.setText("");
            edtEmail.setText("");
            edtEmail.requestFocus();

            adapter.addUser(users);

            Toast.makeText(this, "Inserted ... ", Toast.LENGTH_SHORT).show();
        });

        adapter = new RoomDBAdapter(this, this);
        rcView.setAdapter(adapter);
        rcView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData(){
        adapter.clearData();
        List<Users> list = userDao.getData();

        for (int i = 0; i < list.size(); i++){
            Users users = list.get(i);
            adapter.addUser(users);
        }
    }

    @Override
    public void OnUpdate(Users users) {
        Intent i = new Intent(getApplicationContext(), RoomUpdateAct.class);
        i.putExtra("model", users);
        startActivity(i);
    }

    @Override
    public void OnDelete(int id, int pos) {
        userDao.delete(id);
        adapter.removeUser(pos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}