package com.example.gfgbasics.architecture.mvp.GFG;

public class ContractGFG {
    interface View{
        void showProgress();
        void hideProgress();
        void setString(String string);
    }

    interface Model{
        interface OnFinishedListener{
            void onFinished(String string);
        }
        void getNextCourse(ContractGFG.Model.OnFinishedListener onFinishedListener);
    }

    interface Presenter{
        void onButtonClick();
        void onDestroy();
    }
}
