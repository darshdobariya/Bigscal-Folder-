package com.example.gfgbasics.architecture.mvp.login;

public class LoginContract {
    interface View{
        void showInvalidCredentialError();
        void showLoginSuccessMessage();
    }

    interface Presenter{
        void onLoginButtonClick(String email, String password);
    }

    interface Model{
        boolean login(String email, String password);
    }
}
