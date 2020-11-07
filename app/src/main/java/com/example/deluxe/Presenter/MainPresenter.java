package com.example.deluxe.Presenter;


import android.util.Log;

import com.example.deluxe.Entity.Deposit;
import com.example.deluxe.Interface.Model.DataFirebase;
import com.example.deluxe.Interface.Model.ListDepositInterface;
import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.DepositModel;
import com.example.deluxe.View.DepositActivity;
import com.example.deluxe.View.SignInActivity;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainPresenter implements MainInterface.MainPresenter {

    private MainInterface.MainView mainView;

    public MainPresenter(MainInterface.MainView view)
    {
        this.mainView = view;

        if (!Auth.getInstance().check()) mainView.loadView(SignInActivity.class);

//        new WalletModel().read();
//        new UserModel().read();

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
