package com.example.deluxe.Presenter;

import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.View.DepositActivity;
import com.example.deluxe.View.LoginActivity;

public class MainPresenter implements MainInterface.MainPresenter {

    private MainInterface.MainView mainView;

    public MainPresenter(MainInterface.MainView view)
    {
        this.mainView = view;
    }

    @Override
    public void handleLogOut() {
        Auth.getInstance().logout();
        mainView.loadView(LoginActivity.class);
    }

    @Override
    public void handleNapThe() {
        Auth.getInstance().logout();
        mainView.loadView(DepositActivity.class);
    }
}
