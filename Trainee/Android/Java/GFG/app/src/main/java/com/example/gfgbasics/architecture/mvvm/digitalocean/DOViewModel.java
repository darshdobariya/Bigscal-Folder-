package com.example.gfgbasics.architecture.mvvm.digitalocean;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.gfgbasics.BR;

public class DOViewModel extends BaseObservable {
    private final DODemo demo;

    private String toastMsg = null;

    @Bindable
    public String getToastMsg() {
        return toastMsg;
    }

    public void setToastMsg(String toastMsg) {
        this.toastMsg = toastMsg;
        notifyPropertyChanged(BR.toastMsg);
    }

    @Bindable
    public String getUserEmail() {
        return demo.getEmail();
    }

    public void setUserEmail(String email) {
        demo.setEmail(email);
        notifyPropertyChanged(BR.userEmail);
    }

    @Bindable
    public String getUserPassword() {
        return demo.getPassword();
    }

    public void setUserPassword(String password) {
        demo.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    public DOViewModel() {
        demo = new DODemo("", "");
    }

    public void onLoginClick() {
        String error = "Invalid Credential ... ";
        if (isInputDataValid()) {
            String success = "Logged in ... ";
            setToastMsg(success);
        } else {
            setToastMsg(error);
        }
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(getUserEmail()) &&
                Patterns.EMAIL_ADDRESS.matcher(getUserEmail()).matches() &&
                getUserPassword().length() > 5;
    }
}