package com.example.deluxe.Interface.PresenterView.Components;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Entity.User;

public interface AuthBarInterface {
	interface AuthBarView {
		void setUserInfo(User user);

		void setMoney(double money);
	}

	interface AuthBarPresenter extends Presenter {
	}
}
