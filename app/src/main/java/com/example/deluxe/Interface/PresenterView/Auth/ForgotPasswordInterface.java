package com.example.deluxe.Interface.PresenterView.Auth;

import com.example.deluxe.Core.View;

public interface ForgotPasswordInterface {

    public interface ForgotPasswordPresenter
    {
         void handleSendEmailForgotPassword(String email);
    }

    public interface ForgotPasswordView extends View
    {
    }
}
