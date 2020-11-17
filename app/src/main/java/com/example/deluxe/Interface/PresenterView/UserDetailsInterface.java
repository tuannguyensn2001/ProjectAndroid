package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.User;

public interface UserDetailsInterface {
    interface UserDetailsPresenter extends Presenter {
        void handleDeposit(String serialInput, String cardCodeInput);
    }
    interface UserDetailsView extends View{
	}
}