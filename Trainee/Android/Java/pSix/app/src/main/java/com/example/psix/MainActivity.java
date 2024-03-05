package com.example.psix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtNotes;

    /*What we do?
    -- Implement Note-taking application
    -- Single activity app
    -- Which allows the user to enter a note
    -- Use EditText to take input from the user.
    -- Add a button to reset the note.
    -- The application should have the ability to maintain the state of the EditText field, even after the device is rotated.
    -- This means that when the user rotates the device, the EditText field should retain its previous contents, and the user should be able to continue editing the note without losing any data.*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNotes = findViewById(R.id.edtNotes);
        TextView tvReset = findViewById(R.id.tvReset);
        tvReset.setOnClickListener(v-> edtNotes.setText(""));

        if (savedInstanceState != null){
            edtNotes.setText(savedInstanceState.getString("Notes"));
            Toast.makeText(getApplicationContext(), "Config CHange" + edtNotes.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("Notes", edtNotes.getText().toString());
        super.onSaveInstanceState(outState);
    }
}