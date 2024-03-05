package com.example.gfgbasics.database.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gfgbasics.R;

public class SqliteAct extends AppCompatActivity {
    EditText name, contact, dob;
    Button insert, update, delete, view;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB = new DBHelper(getApplicationContext(), "User_Details.db", null, 1);
        insert.setOnClickListener(view -> {
            String nameTXT = name.getText().toString();
            String contactTXT = contact.getText().toString();
            String dobTXT = dob.getText().toString();

            boolean checkinsertdata = DB.insertData(nameTXT, contactTXT, dobTXT);
            if(checkinsertdata)
                Toast.makeText(getApplicationContext(), "New Entry Inserted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
        });

        update.setOnClickListener(view -> {
            String nameTXT = name.getText().toString();
            String contactTXT = contact.getText().toString();
            String dobTXT = dob.getText().toString();

            boolean checkupdatedata = DB.updateData(nameTXT, contactTXT, dobTXT);
            if(checkupdatedata)
                Toast.makeText(getApplicationContext(), "Entry Updated", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "New Entry Not Updated", Toast.LENGTH_SHORT).show();
        });

        delete.setOnClickListener(view -> {
            String nameTXT = name.getText().toString();

            boolean checkudeletedata = DB.deleteData(nameTXT);
            if(checkudeletedata)
                Toast.makeText(getApplicationContext(), "Entry Deleted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Entry Not Deleted", Toast.LENGTH_SHORT).show();
        });

        view.setOnClickListener(v->{
            Cursor res = null;
            try {
                res = DB.getData();
                if (res != null && res.getCount() > 0) {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Name: " + res.getString(0) + "\n");
                        buffer.append("Contact: " + res.getString(1) + "\n");
                        buffer.append("Date of Birth: " + res.getString(2) + "\n\n");
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setCancelable(true);
                    builder.setTitle("User Entries");
                    builder.setMessage(buffer.toString());
                    builder.show();
                } else {
                    Toast.makeText(view.getContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
                }
            } finally {
                if (res != null) {
                    res.close(); // Close the Cursor to release resources
                }
            }
        });
    }
}