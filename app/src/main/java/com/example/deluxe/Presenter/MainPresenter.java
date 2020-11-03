package com.example.deluxe.Presenter;

import android.util.Log;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.UserInterface;
import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.View.DepositActivity;
import com.example.deluxe.View.LoginActivity;

import java.util.ArrayList;

public class MainPresenter implements MainInterface.MainPresenter {

    private MainInterface.MainView mainView;

    public MainPresenter(MainInterface.MainView view)
    {
        this.mainView = view;

        if (!Auth.getInstance().check()) mainView.loadView(LoginActivity.class);

        User user = new User();
        user.setEmail("vinh");

        new UserModel().search(user, new UserInterface() {
            @Override
            public void dataIsLoaded(ArrayList<User> list) {
                ArrayList<User> userList = list;
            }
        });

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
