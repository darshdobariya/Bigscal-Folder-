package com.example.pthree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*What we do?
    -- Implement Survey application
    -- App will have one activity
    -- On the Home screen display a survey form
    -- Survey form should show questions, 4 options and a button for next question.
    -- Show progress as user answers the question
    -- Once the survey is completed, show a pop-up message thanking the user
    -- Use the custom dialog to thank the user.
    -- Dialog will have UI to show a thanks message, image and button to complete survey
    -- Asks at least 3 questions with 4 options each.
    -- App should ask users about the shopping experience.
    -- You can use any images or placeholder to make UI eye-catchy
    -- App should follow material guidelines
    -- Here's UI for reference
    -- Android Activity*/

    private Button btnNext;
    private Button btnPre;
    private ConstraintLayout consStart, consSurvey, consThank;
    private RadioButton opOne, opTwo, opThree, opFour;
    private TextView tvQuestion;
    private int position;
    private final String[] question = {"How often do you shop on this site?", "products you buy frequently on this site.",
                "How much do you spend on online shopping every month?", "How many times do you shop online in a month?"};
    private final String[] oOne = {"Very often", "Baby food", "Less than 100 USD", "Once"};
    private final String[] oTwo = {"Seldom", "Accessories", "$100 – $500", "Twice"};
    private final String[] oThree = {"Not Often", "Perfumes and Oils", "$500 – $1000", "Thrice"};
    private final String[] oFour = {"Always", "Skincare products", "More than 1000 USD", "More than thrice"};
    private RadioGroup rgOption;
    private CardView cvOne, cvTwo, cvThree, cvFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.btnStart);
        btnNext = findViewById(R.id.btnNext);
        btnPre = findViewById(R.id.btnPre);
        consStart = findViewById(R.id.consStart);
        consSurvey = findViewById(R.id.consSurvey);
        consThank = findViewById(R.id.consThank);
        rgOption = findViewById(R.id.radioGroup);
        opOne = findViewById(R.id.opOne);
        opTwo = findViewById(R.id.opTwo);
        opThree = findViewById(R.id.opThree);
        opFour = findViewById(R.id.opFour);
        tvQuestion = findViewById(R.id.tvQuestion);
        cvOne = findViewById(R.id.cvOne);
        cvTwo = findViewById(R.id.cvTwo);
        cvThree = findViewById(R.id.cvThree);
        cvFour = findViewById(R.id.cvFour);

        btnStart.setOnClickListener(v->{
            consStart.setVisibility(View.GONE);
            consSurvey.setVisibility(View.VISIBLE);

            position = 0;
            setQuestion(position);
        });

        btnPre.setOnClickListener(v->{
            position--;
            setQuestion(position);
        });

        btnNext.setOnClickListener(v->{
            if (userActivity()){
                position++;
                setQuestion(position);
            }else Toast.makeText(this, "Select any one option", Toast.LENGTH_SHORT).show();
        });
    }

    private void setQuestion(int pos){
        if (pos == 4){
            openCustomDialog();
            consSurvey.setVisibility(View.GONE);
            consThank.setVisibility(View.VISIBLE);
        }else{
            if (pos == 0){
                btnPre.setVisibility(View.GONE);
            }else btnPre.setVisibility(View.VISIBLE);

            tvQuestion.setText(question[pos]);
            opOne.setText(oOne[pos]);
            opTwo.setText(oTwo[pos]);
            opThree.setText(oThree[pos]);
            opFour.setText(oFour[pos]);

            btnPre.setText(R.string.pre);
            btnNext.setText(R.string.next);

            rgOption.clearCheck();

            int color = ContextCompat.getColor(getApplicationContext(), R.color.green);
            int color1 = ContextCompat.getColor(getApplicationContext(), R.color.grey);

            cvOne.setBackgroundColor(color);

            if (pos >= 1){
                cvTwo.setBackgroundColor(color);
            }else cvTwo.setBackgroundColor(color1);

            if (pos >= 2){
                cvThree.setBackgroundColor(color);
            }else cvThree.setBackgroundColor(color1);

            if (pos >= 3){
                cvFour.setBackgroundColor(color);
            }else cvFour.setBackgroundColor(color1);
        }
    }

    private void openCustomDialog(){
        Dialog dialog;
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.show();

        Button btnOk = dialog.findViewById(R.id.btnOk);

        btnOk.setOnClickListener(v -> dialog.dismiss());
    }

    private boolean userActivity(){
        if (opOne.isChecked()){
            return true;
        }else if (opTwo.isChecked()){
            return true;
        }else if (opThree.isChecked()){
            return true;
        }else return opFour.isChecked();
    }
}