package com.example.gfgbasics.bars;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gfgbasics.R;
import com.google.android.material.snackbar.Snackbar;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.rtugeek.android.colorseekbar.ColorSeekBar;

import github.com.st235.lib_expandablebottombar.ExpandableBottomBar;
import github.com.st235.lib_expandablebottombar.ExpandableBottomBarMenuItem;

public class BarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        SeekBar seekBar = findViewById(R.id.seekBar);
        TextView tvStatus = findViewById(R.id.tvStatus);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvStatus.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button btnCustomSnack = findViewById(R.id.btnCustomSnack);
        btnCustomSnack.setOnClickListener(v->{
            // create an instance of the snackbar
            final Snackbar snackbar = Snackbar.make(v, "", Snackbar.LENGTH_LONG);

            // inflate the custom_snackbar_view created previously
            View customSnackView = getLayoutInflater().inflate(R.layout.custom_snackbar_view, null);

            // set the background of the default snackbar as transparent
            snackbar.getView().setBackgroundColor(Color.TRANSPARENT);

            // now change the layout of the snackbar
            @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();

            // set padding of the all corners as 0
            snackbarLayout.setPadding(0, 0, 0, 0);

            // register the button from the custom_snackbar_view layout file
            Button bGotoWebsite = customSnackView.findViewById(R.id.gotoWebsiteButton);

            // now handle the same button with onClickListener
            bGotoWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Redirecting to Website", Toast.LENGTH_SHORT).show();
                    snackbar.dismiss();
                }
            });

            // add the custom snack bar layout to snackbar layout
            snackbarLayout.addView(customSnackView, 0);

            snackbar.show();
        });

        TextView tvColorSeekBar = findViewById(R.id.text_view);
        ColorSeekBar colorSeekBar = findViewById(R.id.color_seek_bar);

        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int colorBarPosition, int alphaBarPosition, int color) {
                tvColorSeekBar.setTextColor(color);
            }
        });

//        TextView textViewRange = findViewById(R.id.textViewRange);
//        com.jaygoo.widget.SeekBar seekBar1 = findViewById(R.id.range_seekbar_one);;

        // steps on state progress bar
        String[] descriptionData = {"Step One", "Step Two", "Step Three", "Step Four"};

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

        // button given along with id
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (stateProgressBar.getCurrentStateNumber()) {
                    case 1:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                        break;
                    case 2:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                        break;
                    case 3:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                        break;
                    case 4:
                        stateProgressBar.setAllStatesCompleted(true);
                        break;
                }
            }
        });
    }
}