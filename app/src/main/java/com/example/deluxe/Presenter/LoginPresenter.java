package com.example.deluxe.Presenter;

import android.content.Context;
import android.content.Intent;

import com.example.deluxe.Core.Model;
import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.LoginInterface;
import com.example.deluxe.Model.Type.TypeModel;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.R;
import com.example.deluxe.View.SignUpActivity;

public class LoginPresenter  implements LoginInterface.LoginPresenter {
    LoginInterface.LoginView loginView;
    UserModel userModel;



    public LoginPresenter(LoginInterface.LoginView Activity)
    {
        this.loginView = Activity;
        initModel();
    }

    public void initModel()
    {
        this.userModel = new UserModel();
    }


    @Override
    public void handleLogin(String username,String password)
    {
        User user=new User(username,password);

        boolean check= this.userModel.checkUserExist(user);

        this.loginView.handleLoginResult(check);

    }


}