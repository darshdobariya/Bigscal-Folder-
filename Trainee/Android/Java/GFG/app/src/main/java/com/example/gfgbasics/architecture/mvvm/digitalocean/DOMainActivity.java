package com.example.gfgbasics.architecture.mvvm.digitalocean;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
//import androidx.databinding.BindingAdapter;
//import androidx.databinding.DataBindingUtil;
//import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//import com.example.gfgbasics.BR;
import com.example.gfgbasics.BR;
import com.example.gfgbasics.R;

public class DOMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain);
        ViewDataBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_domain);
        activityMainBinding.setVariable(BR.viewModel, new DOViewModel());
        activityMainBinding.executePendingBindings();
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null) {
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}
