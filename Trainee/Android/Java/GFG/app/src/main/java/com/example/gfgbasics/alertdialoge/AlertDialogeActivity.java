package com.example.gfgbasics.alertdialoge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gfgbasics.R;

public class AlertDialogeActivity extends AppCompatActivity {

    Button btnNormalDialog, btnTextInput, btnSweetAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialoge);

        btnNormalDialog = findViewById(R.id.btnNormalDialog);
        btnTextInput = findViewById(R.id.btnTextInput);
        btnSweetAlert = findViewById(R.id.btnSweetAlert);

        btnNormalDialog.setOnClickListener(v->{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setIcon(R.drawable.home)
                .setMessage("Its a normal Alert Dialog")
                .setTitle("Welcome")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            AlertDialog alertDialog1 = alertDialog.create();
            alertDialog1.show();
        });

        btnTextInput.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final View customDialog = getLayoutInflater().inflate(R.layout.dialog_input, null);
            builder.setView(customDialog);

            builder.setTitle("Input")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            // get data from the AlertDialog to the Activity
                            // EditText editText = customLayout.findViewById(R.id.editText);
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        });

        btnSweetAlert.setOnClickListener(v->{
            Intent i = new Intent(this, SingleAndMultiSelectActivity.class);
            startActivity(i);
        });
    }
}