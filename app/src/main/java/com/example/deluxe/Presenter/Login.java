package com.example.deluxe.Presenter;

import android.view.View;

import com.example.deluxe.Interface.LoginInterface;
import com.example.deluxe.View.MainActivity;

public class Login implements LoginInterface.LoginPresenter {
    LoginInterface.LoginView loginView;



    public  Login(LoginInterface.LoginView Activity)
    {
        this.loginView = Activity;
    }


    @Override
    public void handleLogin(String username,String password) {
        this.loginView.setError();
    }
}
