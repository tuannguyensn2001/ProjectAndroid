package com.example.deluxe.Presenter;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.view.View;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.LoginInterface;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.View.MainActivity;

public class Login implements LoginInterface.LoginPresenter {
    LoginInterface.LoginView loginView;



    public  Login(LoginInterface.LoginView Activity)
    {
        this.loginView = Activity;
    }


    @Override
    public void handleLogin(String username,String password) {
        User user=new User(username,password);
        UserModel userModel=new UserModel();
        boolean check=userModel.checkUserExist(user);
        if(!check) {
            this.loginView.setError();
        }
    }
}
