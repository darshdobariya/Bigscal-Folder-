package com.example.gfgbasics.workmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gfgbasics.R;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class NotifyWorkManager extends AppCompatActivity {

    Switch aSwitch;
    TextView tvTime, tvRepeat;
    String[] select = {"Once", "Daily", "Weekly"};
    String repeat;
    int min, hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_work_manager);
        
        aSwitch = findViewById(R.id.switchs);
        tvTime = findViewById(R.id.tvTime);
        tvRepeat = findViewById(R.id.tvRepeat);

        repeat = select[0];
        
        aSwitch.setOnClickListener(v->{
            if (aSwitch.isChecked()){
                setAlarm();
                Toast.makeText(this, "AlarmOn", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(this, "Alarm Off", Toast.LENGTH_SHORT).show();
        });

        tvTime.setOnClickListener(v->{
            Calendar mcurrentTime = Calendar.getInstance();
            final int[] hours = {mcurrentTime.get(Calendar.HOUR_OF_DAY)};
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    tvTime.setText( selectedHour + ":" + selectedMinute);
                    min = selectedMinute;
                    hours[0] = selectedHour;
                    if (hours[0] > 12){
                        hour = hours[0] - 12;
                    }
                    if (min == 0){
                        min = 60;
                    }
                }
            }, hours[0], minute, false);
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        });

        tvRepeat.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Repeat")
                    .setIcon(R.drawable.home)
                    .setPositiveButton("Ok", (dialog, which) -> {
                        dialog.dismiss();
                    });

            builder.setSingleChoiceItems(select, -1, ((dialog, which) ->{
                repeat = select[which];
                tvRepeat.setText(repeat);
                dialog.dismiss();
            }));

            builder.setCancelable(true);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    public void setAlarm(){
        if(aSwitch.isChecked()){

            OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(MyNotificationWork.class)
                    .setInputData(new Data.Builder().putInt("min", ((hour*60) + min))
                            .putString("repeat", repeat).build())
                    .build();
            Log.e("doSome-----", String.valueOf((hour*60)+min));
            WorkManager.getInstance(this).enqueue(workRequest1);
        }else{
            //Off Alarm
        }
    }
}