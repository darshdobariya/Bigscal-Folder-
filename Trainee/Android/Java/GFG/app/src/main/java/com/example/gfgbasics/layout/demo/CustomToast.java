package com.example.gfgbasics.layout.demo;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gfgbasics.R;

public class CustomToast {

    public static void showCustomToast(String message, Activity activity) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_layout,
                activity.findViewById(R.id.toast_container));

        // Set the text of the TextView of the message
        TextView textView = layout.findViewById(R.id.toast_text);
        textView.setText(message);

        // Create and customize the toast
        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.LEFT, -100, -530);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
