package com.example.gfgbasics.alertdialoge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.gfgbasics.R;

import java.util.Arrays;
import java.util.List;

public class SingleAndMultiSelectActivity extends AppCompatActivity {

    Button btnSingle, btnMulti;
    TextView tvSingle, tvMulti;
    String[] select = new String[]{"Java", "Kotlin", "Rust", "JS", "Python"};
    final boolean[] checkedItems = new boolean[select.length];
    final List<String> selectedItems = Arrays.asList(select);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_and_muti_select);

        btnSingle = findViewById(R.id.btnSingle);
        btnMulti = findViewById(R.id.btnMulti);
        tvSingle = findViewById(R.id.tvSingle);
        tvMulti = findViewById(R.id.tvMulti);

        btnSingle.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Single Item")
                    .setIcon(R.drawable.home)
                    .setPositiveButton("Ok", (dialog, which) -> {
                        dialog.dismiss();
                    });

            builder.setSingleChoiceItems(select, -1, ((dialog, which) ->{
                tvSingle.setText("You Selected " + select[which]);
                dialog.dismiss();
            }));

            builder.setCancelable(true);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        btnMulti.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Multi Item")
                    .setIcon(R.drawable.profile)
                    .setPositiveButton("Ok", (dialog, which) -> {
                        for (int i = 0; i < checkedItems.length; i++) {
                            if (i == 0){
                                tvMulti.setText("");
                            }
                            if (checkedItems[i]) {
                                String text = tvMulti.getText() + " " + selectedItems.get(i);
                                tvMulti.setText(text);
                            }
                        }
                        dialog.dismiss();
                    }).setNeutralButton("Clear All", (dialog, which) ->{
                        Arrays.fill(checkedItems, false);
                    });

            builder.setMultiChoiceItems(select, checkedItems, (dialog, which, isChecked) -> {
                checkedItems[which] = isChecked;
                String currentItem = selectedItems.get(which);
            });


            builder.setCancelable(true);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }
}