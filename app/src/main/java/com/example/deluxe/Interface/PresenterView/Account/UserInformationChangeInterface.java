package com.example.deluxe.Interface.PresenterView.Account;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface UserInformationChangeInterface {
	interface UserInformationChangeView extends View {
		void handleClickButton();
	}

	interface UserInformationChangePresenter extends Presenter {
		void handleInformationChange(String username, String phone, String address);
	}
}
