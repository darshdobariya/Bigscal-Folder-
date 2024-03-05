package com.example.gfgbasics.database.firebase.firestore.introslider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gfgbasics.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class IntroSliderAct extends AppCompatActivity {

    // creating variables for view pager,
    // LinearLayout, adapter and our array list.
    private ViewPager viewPager;
    private LinearLayout dotsLL;
    SliderAdapter adapter;
    private ArrayList<SliderModel> sliderModalArrayList;
    int size;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);

        db = FirebaseFirestore.getInstance();

        // initializing all our views.
        viewPager = findViewById(R.id.idViewPager);
        dotsLL = findViewById(R.id.idLLDots);

        // in below line we are creating a new array list.
        sliderModalArrayList = new ArrayList<>();
        loadDataFromFirebase();

        // calling method to add dots indicator
        addDots(size, 0);

        // below line is use to call on page
        // change listener method.
        viewPager.addOnPageChangeListener(viewListener);
    }

    private void loadDataFromFirebase() {

        // below line is use to get data from Firebase
        // firestore using collection in android.
        db.collection("Slider").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // after getting the data we are calling on success method
                    // and inside this method we are checking if the received
                    // query snapshot is empty or not.
                    if (!queryDocumentSnapshots.isEmpty()) {
                        // if the snapshot is not empty we are hiding our
                        // progress bar and adding our data in a list.
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            // after getting this list we are passing
                            // that list to our object class.
                            SliderModel sliderModal = d.toObject(SliderModel.class);

                            // after getting data from Firebase we are
                            // storing that data in our array list
                            sliderModalArrayList.add(sliderModal);
                        }
                        // below line is use to add our array list to adapter class.
                        adapter = new SliderAdapter(getApplicationContext(), sliderModalArrayList);

                        // below line is use to set our
                        // adapter to our view pager.
                        viewPager.setAdapter(adapter);

                        // we are storing the size of our
                        // array list in a variable.
                        size = sliderModalArrayList.size();
                    } else {
                        // if the snapshot is empty we are displaying a toast message.
                        Toast.makeText(getApplicationContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // we are displaying a toast message when
                        // we get any error from Firebase.
                        Toast.makeText(getApplicationContext(), "Fail to load data..", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addDots(int size, int pos) {
        // inside this method we are
        // creating a new text view.
        TextView[] dots = new TextView[size];

        // below line is use to remove all
        // the views from the linear layout.
        dotsLL.removeAllViews();

        // running a for loop to add number
        // of dots to our slider.
        for (int i = 0; i < size; i++) {
            // below line is use to add the dots
            // and modify its color.
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("•"));
            dots[i].setTextSize(35);

            // below line is called when the
            // dots are not selected.
            dots[i].setTextColor(getResources().getColor(R.color.white));
            dotsLL.addView(dots[i]);
        }
        if (dots.length > 0) {
            // this line is called when the dots
            // inside linear layout are selected
            dots[pos].setTextColor(getResources().getColor(R.color.color9));
        }
    }

    // creating a method for view pager for on page change listener.
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            // we are calling our dots method to
            // change the position of selected dots.
            addDots(size, position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}