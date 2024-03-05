package com.example.gfgbasics20.facebookads;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gfgbasics20.R;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class FaceBookAdsActivity extends AppCompatActivity {

    EditText codeEt;
    Button joinBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_book_ads);
        codeEt = findViewById(R.id.conferenceName);
        joinBtn = findViewById(R.id.joinBtn);

        try {
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL("https://meet.jit.si"))
                    .build();
            JitsiMeet.setDefaultConferenceOptions(options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = codeEt.getText().toString();
                if (code.length()>0){
                    JitsiMeetConferenceOptions roomOptions = new JitsiMeetConferenceOptions.Builder().setRoom("test123").build();
                    JitsiMeetActivity.launch(FaceBookAdsActivity.this,roomOptions);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please add a appropriate code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}