package com.example.pten;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.Objects;

public class QuickSendActivity extends AppCompatActivity {

    Button button;
    AppCompatEditText sendto, subject, body;

    /*What we do?
    -- Develop QuickSend application
    -- This will be single Activity app - QuickSendActivity
    -- QuickSendActivity allows users to send emails
    -- Add EditTextxs to input the receiver's email address and email content.
    -- Add a button to send email
    -- On click of it, the app should ask for the app to choose to send mail on.
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendto = findViewById(R.id.edtMail);
        subject = findViewById(R.id.edtSubject);
        body = findViewById(R.id.edtBody);
        button = findViewById(R.id.btnSend);

        button.setOnClickListener(view -> {
            String emailsend = Objects.requireNonNull(sendto.getText()).toString();
            String emailsubject = Objects.requireNonNull(subject.getText()).toString();
            String emailbody = Objects.requireNonNull(body.getText()).toString();

            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailsend});
            intent.putExtra(Intent.EXTRA_SUBJECT, emailsubject);
            intent.putExtra(Intent.EXTRA_TEXT, emailbody);

            intent.setType("message/rfc822");

            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        });
    }
}