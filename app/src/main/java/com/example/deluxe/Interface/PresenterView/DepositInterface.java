package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface DepositInterface {
    interface DepositPresenter extends Presenter {
        void handleDeposit(String serialInput, String cardCodeInput);
    }
    interface DepositView extends View{
        void setMoney(double money);
    }
}