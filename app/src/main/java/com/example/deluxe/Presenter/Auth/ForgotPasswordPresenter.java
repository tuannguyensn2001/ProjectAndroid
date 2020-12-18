package com.example.deluxe.Presenter.Auth;

import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Interface.Model.SendEmailInterface;
import com.example.deluxe.Interface.PresenterView.Auth.ForgotPasswordInterface;
import com.example.deluxe.Model.Auth;

public class ForgotPasswordPresenter implements ForgotPasswordInterface.ForgotPasswordPresenter {

    private ForgotPasswordInterface.ForgotPasswordView forgotPasswordView;

    public ForgotPasswordPresenter(ForgotPasswordInterface.ForgotPasswordView forgotPasswordView)
    {
        this.forgotPasswordView=forgotPasswordView;
    }

    @Override
    public void handleSendEmailForgotPassword(String email) {
        Auth.getInstance().forgotPassword(email, new SendEmailInterface() {
            @Override
            public void successful() {
                forgotPasswordView.setNotification(SuccessMessage.SUC000002);
            }

            @Override
            public void failed() {

            }
        });
    }
}
