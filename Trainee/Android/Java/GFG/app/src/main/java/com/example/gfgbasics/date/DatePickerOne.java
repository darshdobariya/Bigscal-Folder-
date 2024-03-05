package com.example.gfgbasics.date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gfgbasics.R;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialog;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialogCallback;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePickerOne extends AppCompatActivity implements SlideDatePickerDialogCallback {

    final Calendar calendar = Calendar.getInstance();
    boolean isWorking = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_one);

        DatePicker datePicker = findViewById(R.id.datePicker);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> Toast.makeText(DatePickerOne.this, year + " " + monthOfYear + " " + dayOfMonth, Toast.LENGTH_SHORT).show());
        }

        TimePicker timePicker = findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> Toast.makeText(DatePickerOne.this, hourOfDay + " " + minute, Toast.LENGTH_SHORT).show());

        TextView tvCountDownTimer = findViewById(R.id.tvCountDownTImer);

        new CountDownTimer(50000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                tvCountDownTimer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }

            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                tvCountDownTimer.setText("00:00:00");
            }
        }.start();

        Button btnClick = findViewById(R.id.btnClick);
        TextView tvDate = findViewById(R.id.tvDate);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                String myFormat = "MM/dd/yy";
                SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                tvDate.setText(dateFormat.format(calendar.getTime()));
            }
        };

        btnClick.setOnClickListener(v -> {
            new DatePickerDialog(this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        Chronometer chronometer = findViewById(R.id.chMeter);
        Button btnChmeter = findViewById(R.id.btnChmeter);

        btnChmeter.setOnClickListener(v -> {
            if (!isWorking) {
                isWorking = true;
                btnClick.setText("Stop");
                chronometer.start();
            } else {
                isWorking = false;
                btnClick.setText("Start");
                chronometer.stop();
            }
        });

        Button btnSliderDate = findViewById(R.id.btnSliderDate);

        btnSliderDate.setOnClickListener(v -> {
            calendar.set(Calendar.YEAR, 2100);
            SlideDatePickerDialog.Builder builder = new SlideDatePickerDialog.Builder();
            builder.setEndDate(calendar);
            SlideDatePickerDialog dialog = builder.build();
            dialog.show(getSupportFragmentManager(), "Dialog");
        });
    }

    @Override
    public void onPositiveClick(int i, int i1, int i2, @NonNull Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMM dd, yyyy", Locale.getDefault());
        TextView tvSliderDate = findViewById(R.id.tvSliderDate);
        tvSliderDate.setText(format.format(calendar.getTime()));
    }
}