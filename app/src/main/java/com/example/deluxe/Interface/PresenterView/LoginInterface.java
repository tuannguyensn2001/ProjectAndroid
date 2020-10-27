package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.User;

import java.util.ArrayList;

public interface LoginInterface  {
    public interface LoginView extends View {
        void handleClickButton();
        void setError(String value);
        void handleLoginResult(boolean check);

    }

    public interface LoginPresenter extends Presenter {
        void handleLogin(String usernane,String password);

    }


}
