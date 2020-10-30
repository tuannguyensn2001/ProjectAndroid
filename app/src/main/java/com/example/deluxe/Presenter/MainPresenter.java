package com.example.deluxe.Presenter;

import android.util.Log;

import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.View.DepositActivity;
import com.example.deluxe.View.LoginActivity;

public class MainPresenter implements MainInterface.MainPresenter {

    private MainInterface.MainView mainView;

    public MainPresenter(MainInterface.MainView view)
    {
        this.mainView = view;

        if (!Auth.getInstance().check()) mainView.loadView(LoginActivity.class);

    }

    @Override
    public void handleLogOut() {
        Auth.getInstance().logout();
        mainView.loadView(LoginActivity.class);
    }

    @Override
    public void handleNapThe() {
        mainView.loadView(DepositActivity.class);
    }
}
