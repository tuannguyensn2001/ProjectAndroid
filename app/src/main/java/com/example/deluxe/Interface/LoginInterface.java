package com.example.deluxe.Interface;

public interface LoginInterface {
    public interface LoginView{
        void handleClickButton();
        void setError(String error);
    }

    public interface LoginPresenter{
        void handleLogin(String username,String password);
    }
}
