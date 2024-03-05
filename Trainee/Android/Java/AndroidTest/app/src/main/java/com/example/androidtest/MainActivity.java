package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtest.homefragment.EventFragment;
import com.example.androidtest.homefragment.ProfileFragment;
import com.example.androidtest.homefragment.SettingFragment;

public class MainActivity extends AppCompatActivity {

    CardView cvProfile, cvSetting, cvEvent;
    TextView tvProfile, tvSetting,tvEvent;
    ImageView imgProfile, imgSetting, imgEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cvProfile = findViewById(R.id.cvProfile);
        cvSetting = findViewById(R.id.cvSetting);
        cvEvent = findViewById(R.id.cvEvent);
        tvProfile = findViewById(R.id.tvProfile);
        tvSetting = findViewById(R.id.tvSetting);
        tvEvent = findViewById(R.id.tvEvent);
        imgProfile = findViewById(R.id.imgProfile);
        imgSetting = findViewById(R.id.imgSetting);
        imgEvent = findViewById(R.id.imgEvent);

        selectFragment(new EventFragment(), imgEvent, tvEvent, cvEvent);

        cvEvent.setOnClickListener(v-> selectFragment(new EventFragment(), imgEvent, tvEvent, cvEvent));

        cvProfile.setOnClickListener(v-> selectFragment(new ProfileFragment(), imgProfile, tvProfile, cvProfile));

        cvSetting.setOnClickListener(v-> selectFragment(new SettingFragment(), imgSetting, tvSetting, cvSetting));
    }

    @SuppressLint("ResourceAsColor")
    private void selectFragment(Fragment fragment, ImageView imgView, TextView textView, CardView cardView){
        if (fragment != null) {

            resetNavigationBar();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frmMain, fragment)
                    .commit();

            // set image color of selected fragment icon
            imgView.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);

            // set font style of selected fragment text
            Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.roboto_bold);
            textView.setTypeface(typeface);
            textView.setTextColor(Color.WHITE);

            // set card background tint
            cardView.setCardBackgroundColor(R.color.purple_background);
        }
    }

    @SuppressLint("ResourceAsColor")
    private void resetNavigationBar(){
        // set image color of selected fragment icon
        imgEvent.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
        imgProfile.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
        imgSetting.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);

        // set font style of selected fragment text
        Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.roboto_regular);
        tvEvent.setTypeface(typeface);
        tvEvent.setTextColor(Color.BLACK);

        tvProfile.setTypeface(typeface);
        tvProfile.setTextColor(Color.BLACK);

        tvSetting.setTypeface(typeface);
        tvSetting.setTextColor(Color.BLACK);

        // set card background tint
        cvEvent.setCardBackgroundColor(Color.TRANSPARENT);
        cvProfile.setCardBackgroundColor(Color.TRANSPARENT);
        cvSetting.setCardBackgroundColor(Color.TRANSPARENT);
    }
}