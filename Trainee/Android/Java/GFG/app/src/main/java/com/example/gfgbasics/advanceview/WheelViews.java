package com.example.gfgbasics.advanceview;

import static android.app.ProgressDialog.show;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gfgbasics.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import andreasagap.loadingbutton.ButtonLoading;

public class WheelViews extends AppCompatActivity {

//    ButtonLoading loading_button;
//    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_view);

//        layout = findViewById(R.id.root);
//        loading_button =findViewById(R.id.loadingbutton);
//        loading_button.setRoot(loading_button,this, layout);
//
//        //set OnClickListener to button
//        loading_button.setOnButtonLoadingListener(
//                new ButtonLoading.OnButtonLoadingListener() {
//                    @Override
//                    public void onClick() {
//                    }
//                    @Override
//                    public void onStart() {
//                    }
//                    @Override
//                    public void onFinish() {
//                        //show snackbar when loading finished
//                        Snackbar.make(layout, "Thank you for buying our Course!",
//                        BaseTransientBottomBar
//                                .LENGTH_LONG)
//                       .show();
//                    }
//                });
//    }
//
//    @Override
//    public void onBackPressed() {
//        loading_button.cancel();
    }
}