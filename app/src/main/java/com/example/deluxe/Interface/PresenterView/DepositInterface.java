package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.View;

public interface DepositInterface {
    public interface DepositPresenter{
        void handleDeposit(String serialInput, String cardCodeInput);
    }
    public interface DepositView extends View{
        void setError(String s);
        void setMoney(double money);
    }


}