package com.example.gfgbasics.architecture.mvp.GFG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.model.Model;
import com.example.gfgbasics.R;

public class GFGMVPActivity extends AppCompatActivity implements ContractGFG.View{

    private ContractGFG.Presenter presenter;
    private TextView tvCourseDes;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gfgmvpactivity);

        tvCourseDes = findViewById(R.id.tvCourseDes);
        Button btnClick = findViewById(R.id.btnClick);
        progressBarInit();

        presenter = new PresenterGFG(this, new ViewModelGFG());

        btnClick.setOnClickListener(v->{
            presenter.onButtonClick();
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        tvCourseDes.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        tvCourseDes.setVisibility(View.VISIBLE);
    }

    @Override
    public void setString(String string) {
        tvCourseDes.setText(string);
    }

    private void progressBarInit(){
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels,250);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addContentView(progressBar, params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}