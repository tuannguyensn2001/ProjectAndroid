package com.example.deluxe.Interface.PresenterView.Account;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.Address;

public interface UserInformationChangeInterface {
	interface UserInformationChangeView extends View {
		void handleClickButton();

		void setData(Address address);
	}

	interface UserInformationChangePresenter extends Presenter {
		void handleInformationChange(String username, String phone, String address);
	}
}
