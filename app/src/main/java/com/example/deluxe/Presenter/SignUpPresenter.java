package com.example.deluxe.Presenter;

import com.example.deluxe.Interface.PresenterView.SignUpInterface;

public class SignUpPresenter  implements SignUpInterface.SignUpPresenter {
    SignUpInterface.SignUpView signUpView;

    public SignUpPresenter(SignUpInterface.SignUpView view){
        this.signUpView=view;
    }
    @Override
     public void handleSignUp(String username, String password, String email){
    }
}
