package com.example.deluxe.Interface;

public interface LoginInterface {
    public interface LoginView{
        void handleClickButton();
        void setError();
    }

    public interface LoginPresenter{
        void handleLogin(String usernane,String password);
    }
}
