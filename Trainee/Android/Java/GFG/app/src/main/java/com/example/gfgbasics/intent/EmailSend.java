package com.example.gfgbasics.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.gfgbasics.R;

public class EmailSend extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_send);

        button = findViewById(R.id.btnSend);

        button.setOnClickListener(view -> {
            // define Intent object with action attribute as ACTION_SEND
            Intent intent = new Intent(Intent.ACTION_SEND);

            // add three fields to intent using putExtra function
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"darshdobariya3@gmail.com", "kdobariya987@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "emailsubject");
            intent.putExtra(Intent.EXTRA_TEXT, "emailbody");

            // set type of intent
            intent.setType("message/rfc822");

            // startActivity with intent with chooser as Email client using createChooser function
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        });
    }
}