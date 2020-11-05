package com.example.deluxe.Presenter;

import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.View.DepositActivity;
import com.example.deluxe.View.SignInActivity;

public class MainPresenter implements MainInterface.MainPresenter {

    private MainInterface.MainView mainView;

    public MainPresenter(MainInterface.MainView view)
    {
        this.mainView = view;

        if (!Auth.getInstance().check()) mainView.loadView(SignInActivity.class);

    }

    @Override
    public void handleLogOut() {
        Auth.getInstance().logout();
        mainView.loadView(SignInActivity.class);
    }

    @Override
    public void handleDeposit() {
        mainView.loadView(DepositActivity.class);
    }
}
