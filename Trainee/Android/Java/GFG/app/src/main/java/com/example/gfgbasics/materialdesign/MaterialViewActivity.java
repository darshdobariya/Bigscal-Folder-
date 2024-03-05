package com.example.gfgbasics.materialdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.util.Pair;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gfgbasics.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

public class MaterialViewActivity extends AppCompatActivity {

    ImageView img1, img2;
    String one = "https://healthraylive-mhr.s3.ap-south-1.amazonaws.com/family_members/1086550/thumb/file_Android_1816_9848755037071.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIASDXOGTGCNDR3NBNJ%2F20240207%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20240207T114028Z&X-Amz-Expires=86400&X-Amz-Signature=be19a0e3c79ebfd578c4b3bdffae81b5961fdc816b3ba0d01d20ae626eb1922f&X-Amz-SignedHeaders=host";
    String two = "https://healthraylive-mhr.s3.ap-south-1.amazonaws.com/family_members/undefined/thumb/file_Android_1816_9848755037071.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIASDXOGTGCNDR3NBNJ%2F20240207%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20240207T113850Z&X-Amz-Expires=86400&X-Amz-Signature=19dca9ed7d6a8a1e6ba6a12556ea189810da96fbc6c4bab2dc69f7b2ccd03f34&X-Amz-SignedHeaders=host";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_view);

        img1 = findViewById(R.id.ivOne);
        img2 = findViewById(R.id.ivTwo);

        Glide.with(this)
                .load(one).
                into(img1);

        Glide.with(this)
                .load(two).
                into(img2);

        Button btnMaterial = findViewById(R.id.btnMaterial);
        btnMaterial.setOnClickListener(v -> {
            MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();

            // now define the properties of the
            // materialDateBuilder
            materialDateBuilder.setTitleText("SELECT A DATE");

            // now create the instance of the material date
            // picker
            final MaterialDatePicker<Pair<Long, Long>> materialDatePicker = materialDateBuilder.build();
            materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        });

        Button btnCalender1 = findViewById(R.id.btnCalender1);
        btnCalender1.setOnClickListener(v->{

            // create the instance of the calendar to set the
            // bounds
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

            // now set the starting bound from current month to
            // previous MARCH
            calendar.set(Calendar.MONTH, Calendar.MARCH);
            long march = calendar.getTimeInMillis();

            // now set the ending bound from current month to
            // DECEMBER
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            long december = calendar.getTimeInMillis();


            // create the calendar constraint builder
            CalendarConstraints.Builder calendarConstraintBuilder = new CalendarConstraints.Builder();

            // set the validator point forward from june
            // this mean the all the dates before the June month
            // are blocked
            calendarConstraintBuilder.setValidator(DateValidatorPointForward.now());

            // and set the start and end constraints (bounds)
            calendarConstraintBuilder.setStart(march);
            calendarConstraintBuilder.setEnd(december);

            // instantiate the Material date picker dialog
            // builder
            final MaterialDatePicker.Builder<Long> materialDatePickerBuilder = MaterialDatePicker.Builder.datePicker();
            materialDatePickerBuilder.setTitleText("SELECT A DATE");

            // set selected date as a tod
            long today = MaterialDatePicker.todayInUtcMilliseconds();
            materialDatePickerBuilder.setSelection(today);

            // now pass the constrained calendar builder to
            // material date picker Calendar constraints
            materialDatePickerBuilder.setCalendarConstraints(calendarConstraintBuilder.build());

            // now build the material date picker dialog
            final MaterialDatePicker<Long> materialDatePicker = materialDatePickerBuilder.build();
            materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        });

        //this add for swipe to remove snackbar
        CoordinatorLayout mSnackbarLayout = findViewById(R.id.snackbar_layout);
        Button btnSnackBar1 = findViewById(R.id.btnSnackBar1);
        btnSnackBar1.setOnClickListener(v->{

            // we used mSnackbarLayout because here we added swite remover either add button View as v
            Snackbar snackbar = Snackbar.make(mSnackbarLayout,"It's My first SnackBar",Snackbar.LENGTH_SHORT);
            snackbar.setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MaterialViewActivity.this, "item restored", Toast.LENGTH_SHORT).show();
                }
            });
            // If want to add duration
            snackbar.setDuration(3000);

            // snackbar show
            snackbar.show();
        });

        Button btnCustomSnackbar = findViewById(R.id.btnCustomSnackbar);
        btnCustomSnackbar.setOnClickListener(v->{
            Snackbar snackbar = Snackbar.make(v, "Custom SnackBar For you", Snackbar.LENGTH_SHORT);
            snackbar.setAction("Ok", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MaterialViewActivity.this, "Shuhhhh!!!!!", Toast.LENGTH_SHORT).show();
                }
            });
            snackbar.setDuration(2000);
            snackbar.setBackgroundTint(getResources().getColor(R.color.color57));
            // set the action button text color of the snackbar however this is optional
            // as all the snackbar wont have the action button
            snackbar.setActionTextColor(getResources().getColor(R.color.color35));
            snackbar.show();
        });

        Chip chipCpp, chipJava, chipPython;
        chipCpp = findViewById(R.id.chipCpp);
        chipJava = findViewById(R.id.chipJava);
        chipPython = findViewById(R.id.chipPython);

        if (chipCpp.isChecked()){
            chipCpp.setChipBackgroundColor(ColorStateList.valueOf(Color.RED));
        }else chipCpp.setBackgroundColor(Color.TRANSPARENT);

        if (chipJava.isChecked()){
            chipJava.setBackgroundColor(Color.RED);
        }else chipJava.setBackgroundColor(Color.TRANSPARENT);

        if (chipPython.isChecked()){
            chipPython.setBackgroundColor(Color.RED);
        }else chipPython.setBackgroundColor(Color.TRANSPARENT);
    }
}