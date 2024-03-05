package com.example.pfifteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button btnNext;
    private ConstraintLayout consStart, consSurvey, consThank;
    private RadioButton opOne, opTwo, opThree, opFour;
    private TextView tvQuestion, tvResult, tvEmotion;
    private int position;
    private final String[] questions = {
            "",
            "What is the value of 2 + 2?",
            "What is the result of 5 × 6?",
            "What is the square root of 81?",
            "What is the value of 3/4 + 1/4?",
            "If x + 3 = 10, what is the value of x?",
            "What is 10% of 200?",
            "What is 1/2 expressed as a decimal?",
            "If 2x = 10, what is the value of x?",
            "What is 2^3?",
            "What is the area of a rectangle with length 5 units and width 3 units?"
    };

    private final String[][] options = {
            {},
            {"3", "4", "5", "6", "1"},
            {"20", "25", "30", "35", "2"},
            {"7", "8", "9", "10", "2"},
            {"1/2", "2/3", "4/5", "4/4", "3"},
            {"6", "7", "8", "9", "1"},
            {"20", "30", "40", "50", "0"},
            {"0.25", "0.5", "0.75", "1.0", "1"},
            {"3", "4", "5", "6", "2"},
            {"4", "6", "8", "10", "1"},
            {"8 square units", "12 square units", "15 square units", "18 square units", "2"}
    };

    private boolean[] result;
    private int check;
    private RadioGroup rgOption;
    private CardView cvOne, cvTwo, cvThree, cvFour, cvFive, cvSix, cvSeven, cvEight, cvNine, cvTen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.btnStart);
        btnNext = findViewById(R.id.btnNext);
        Button btnRetake = findViewById(R.id.btnRetake);
        consStart = findViewById(R.id.consStart);
        consSurvey = findViewById(R.id.consSurvey);
        consThank = findViewById(R.id.consThank);
        rgOption = findViewById(R.id.radioGroup);
        opOne = findViewById(R.id.opOne);
        opTwo = findViewById(R.id.opTwo);
        opThree = findViewById(R.id.opThree);
        opFour = findViewById(R.id.opFour);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvResult = findViewById(R.id.tvResult);
        tvEmotion = findViewById(R.id.tvEmotion);
        cvOne = findViewById(R.id.cvOne);
        cvTwo = findViewById(R.id.cvTwo);
        cvThree = findViewById(R.id.cvThree);
        cvFour = findViewById(R.id.cvFour);
        cvFive = findViewById(R.id.cvFive);
        cvSix = findViewById(R.id.cvSix);
        cvSeven = findViewById(R.id.cvSeven);
        cvEight = findViewById(R.id.cvEight);
        cvNine = findViewById(R.id.cvNine);
        cvTen = findViewById(R.id.cvTen);

        result = new boolean[11];
        result[0]  = false;

        btnStart.setOnClickListener(v-> startQuiz());

        btnNext.setOnClickListener(v->{
            if (userActivity()){
                result[position] = Integer.parseInt(options[position][4]) == check;

                position++;
                setQuestion(position);
            }else Toast.makeText(this, "Select any one option", Toast.LENGTH_SHORT).show();
        });

        btnRetake.setOnClickListener(v-> startQuiz());
    }

    private void startQuiz(){
        consStart.setVisibility(View.GONE);
        consSurvey.setVisibility(View.VISIBLE);
        consThank.setVisibility(View.GONE);

        position = 1;
        setQuestion(position);
    }

    private void setQuestion(int pos){
        if (pos == 11){
            consSurvey.setVisibility(View.GONE);
            consThank.setVisibility(View.VISIBLE);
            showResult();
        }else{

            tvQuestion.setText(questions[pos]);
            opOne.setText(options[pos][0]);
            opTwo.setText(options[pos][1]);
            opThree.setText(options[pos][2]);
            opFour.setText(options[pos][3]);

            btnNext.setText(R.string.next);

            rgOption.clearCheck();

            int color = ContextCompat.getColor(getApplicationContext(), R.color.green);
            int color1 = ContextCompat.getColor(getApplicationContext(), R.color.grey);

            cvOne.setBackgroundColor(color);

            if (pos >= 2){
                cvTwo.setBackgroundColor(color);
            }else cvTwo.setBackgroundColor(color1);

            if (pos >= 3){
                cvThree.setBackgroundColor(color);
            }else cvThree.setBackgroundColor(color1);

            if (pos >= 4){
                cvFour.setBackgroundColor(color);
            }else cvFour.setBackgroundColor(color1);

            if (pos >= 5){
                cvFive.setBackgroundColor(color);
            }else cvFive.setBackgroundColor(color1);

            if (pos >= 6){
                cvSix.setBackgroundColor(color);
            }else cvSix.setBackgroundColor(color1);

            if (pos >= 7){
                cvSeven.setBackgroundColor(color);
            }else cvSeven.setBackgroundColor(color1);

            if (pos >= 8){
                cvEight.setBackgroundColor(color);
            }else cvEight.setBackgroundColor(color1);

            if (pos >= 9){
                cvNine.setBackgroundColor(color);
            }else cvNine.setBackgroundColor(color1);

            if (pos >= 10){
                cvTen.setBackgroundColor(color);
            }else cvTen.setBackgroundColor(color1);
        }
    }

    private boolean userActivity(){
        if (opOne.isChecked()){
            check = 0;
            return true;
        }else if (opTwo.isChecked()){
            check = 1;
            return true;
        }else if (opThree.isChecked()){
            check = 2;
            return true;
        }else if (opFour.isChecked()){
            check = 3;
            return true;
        }
        return false;
    }

    private void showResult(){
        int score = 0;
        for(int i = 0; i < result.length; i++){
            if (result[i]){
                score++;

                Log.e("Print item", i + " - " + Arrays.toString(result));
            }
        }

        String result = score + "/10";
        tvResult.setText(result);

        if (score > 8){
            tvEmotion.setText(R.string.exellent);
            tvEmotion.setTextColor(Color.GREEN);
        }else if (score > 5){
            tvEmotion.setText(R.string.good);
            tvEmotion.setTextColor(Color.YELLOW);
        }else {
            tvEmotion.setText(R.string.bad);
            tvEmotion.setTextColor(Color.RED);
        }
    }
}