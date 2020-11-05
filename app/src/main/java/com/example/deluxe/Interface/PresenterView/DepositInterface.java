package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.View;
import com.example.deluxe.Enum.ErrorMessage;

public interface DepositInterface {
    public interface DepositPresenter{
        void handleDeposit(String serialInput, String cardCodeInput);
    }
    public interface DepositView extends View{
        void setMoney(double money);
    }


}