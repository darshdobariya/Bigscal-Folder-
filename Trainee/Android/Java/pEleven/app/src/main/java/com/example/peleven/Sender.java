package com.example.peleven;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

// start activity

public class Sender extends AppCompatActivity {
    private ActivityResultLauncher<Intent> launcher;
    private TextView tvSend;

    /*What we do?
    -- Develop the TalkEasy application
    -- The app will send and receive messages between two activities.
    -- The app will use two Activities - Sender & Receiver Activities.
    -- The Sender activity should have an edit text and a send button
    -- When the user enters a message and clicks on the send button, open Receiver activity and show the message recieved from Sender Activity.
    -- The Receiver activity should have an edit text and a reply button
    -- When the user enters a reply message and clicks on the reply button, the replied message should be sent back to the Sender activity and displayed in a text view.*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatEditText edtSend = findViewById(R.id.edtSend);
        Button btnSend = findViewById(R.id.btnSend);
        tvSend = findViewById(R.id.tvSend);

        btnSend.setOnClickListener(v -> {
            Intent i = new Intent(this, Reciever.class);
            i.putExtra("Text", Objects.requireNonNull(edtSend.getText()).toString());
            launcher.launch(i);
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    String returnValue = data.getStringExtra("Text");
                    tvSend.setText(returnValue);
                }
            }
        });
    }
}