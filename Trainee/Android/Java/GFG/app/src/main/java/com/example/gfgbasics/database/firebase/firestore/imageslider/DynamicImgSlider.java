package com.example.gfgbasics.database.firebase.firestore.imageslider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.gfgbasics.MainActivity;
import com.example.gfgbasics.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class DynamicImgSlider extends AppCompatActivity {
    private SliderAdapter adapter;
    private ArrayList<SliderData> sliderDataArrayList;
    FirebaseFirestore db;
    private SliderView sliderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_img_slider);

        sliderDataArrayList = new ArrayList<>();

        sliderView = findViewById(R.id.slider);
        db = FirebaseFirestore.getInstance();

        initializeWebView();

        loadImages();
    }

    private void initializeWebView() {

        // initializing variable for web view.
        WebView webView = findViewById(R.id.webview);

        // below line is used to get the instance
        // of our Firebase database.
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        DatabaseReference databaseReference = firebaseDatabase.getReference("url");

        // calling add value event listener method for getting the values from database.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime updates in the data.
                // this method is called when the data is changed in our Firebase console.
                // below line is for getting the data from snapshot of our database.
                String webUrl = snapshot.getValue(String.class);

                // after getting the value for our webview url we are
                // setting our value to our webview view in below line.
                webView.loadUrl(webUrl);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(getApplicationContext(), "Fail to get URL.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadImages() {
        // getting data from our collection and after
        // that calling a method for on success listener.
        db.collection("Slider").get().addOnSuccessListener(queryDocumentSnapshots -> {

            // inside the on success method we are running a for loop
            // and we are getting the data from Firebase Firestore
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                // after we get the data we are passing inside our object class.
                SliderData sliderData = documentSnapshot.toObject(SliderData.class);
                SliderData model = new SliderData();

                // below line is use for setting our
                // image url for our modal class.
                model.setImgUrl(sliderData.getImgUrl());

                // after that we are adding that
                // data inside our array list.
                sliderDataArrayList.add(model);

                // after adding data to our array list we are passing
                // that array list inside our adapter class.
                adapter = new SliderAdapter(getApplicationContext(), sliderDataArrayList);

                // below line is for setting adapter
                // to our slider view
                sliderView.setSliderAdapter(adapter);

                // below line is for setting animation to our slider.
                sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

                // below line is for setting auto cycle duration.
                sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);

                // below line is for setting
                // scroll time animation
                sliderView.setScrollTimeInSec(5);

                // below line is for setting auto
                // cycle animation to our slider
                sliderView.setAutoCycle(true);

                // below line is use to start
                // the animation of our slider view.
                sliderView.startAutoCycle();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we get any error from Firebase we are
                // displaying a toast message for failure
                Toast.makeText(getApplicationContext(), "Fail to load slider data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}