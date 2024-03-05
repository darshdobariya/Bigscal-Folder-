package com.example.gfgbasics.architecture.mvp.login;

    public class LoginPresenter implements LoginContract.Presenter{

    private final LoginContract.View mView;
    private final LoginContract.Model mModel;

    public LoginPresenter(LoginContract.View view){
        mView = view;
        mModel = new LoginModel();
    }

    @Override
    public void onLoginButtonClick(String email, String password) {
        if (mModel.login(email, password)){
            mView.showLoginSuccessMessage();
        }else {
            mView.showInvalidCredentialError();
        }
    }
}