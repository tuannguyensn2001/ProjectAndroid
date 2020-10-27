package com.example.deluxe.Presenter;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.PresenterView.LoginInterface;
import com.example.deluxe.Model.UserModel;

public class LoginPresenter  implements LoginInterface.LoginPresenter{
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

//        boolean check=

        this.loginView.handleLoginResult(true);


    }




}