package com.example.gfgbasics.architecture.mvp.GFG;

public class PresenterGFG implements ContractGFG.Presenter, ContractGFG.Model.OnFinishedListener{

    private ContractGFG.View view;
    private final ContractGFG.Model model;

    public PresenterGFG(ContractGFG.View view, ContractGFG.Model model){
        this.view = view;
        this.model = model;
    }

    @Override
    public void onButtonClick() {
            if (view != null){
            view.showProgress();
        }
        model.getNextCourse(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onFinished(String string) {
        if (view != null){
            view.setString(string);
            view.hideProgress();
        }
    }
}
