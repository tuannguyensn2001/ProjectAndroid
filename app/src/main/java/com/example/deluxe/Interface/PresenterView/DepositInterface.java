package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.User;

public interface DepositInterface {
    interface DepositPresenter extends Presenter {
        void handleDeposit(String serialInput, String cardCodeInput);
    }
    interface DepositView extends View{
        void setMoney(double money);

		void setUserInfo(User user);
	}
}