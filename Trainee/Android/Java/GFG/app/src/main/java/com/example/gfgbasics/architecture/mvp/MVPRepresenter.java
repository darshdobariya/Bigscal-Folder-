package com.example.gfgbasics.architecture.mvp;

public class MVPRepresenter {
    private MVPDemo user;
    private View view;

    public MVPRepresenter(View view){
        this.user = new MVPDemo();
        this.view = view;
    }

    public void updateFullName(String fullName){
        user.setUserName(fullName);
        view.updateUserInfoTextView(user.toString());
    }

    public void updateEmail(String email){
        user.setMail(email);
        view.updateUserInfoTextView(user.toString());
    }

    public interface View{
        void updateUserInfoTextView(String info);
        void showProgressBar();
        void hideProgressBar();
    }
}