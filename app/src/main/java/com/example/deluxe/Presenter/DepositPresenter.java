package com.example.deluxe.Presenter;

import com.example.deluxe.Interface.PresenterView.DepositInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.View.MainActivity;

public class DepositPresenter implements DepositInterface.DepositPresenter {
    DepositInterface.DepositView depositView;
    UserModel userModel;

    public DepositPresenter(DepositInterface.DepositView depositView) {
        this.depositView = depositView;

        initModel();
        checkAuth();
    }

    @Override
    public void handleDeposit(String serialInput, String cardCodeInput) {
//		if(Auth.getInstance().isHetlannap())
//			depositView.loadView(MainActivity.class);
//		else Auth.getInstance().naptien(serialInput, cardCodeInput)
    }

    private void initModel() {
        this.userModel = new UserModel();
    }

    public void checkAuth() {
        if (Auth.getInstance().check()) depositView.loadView(MainActivity.class);
    }
}