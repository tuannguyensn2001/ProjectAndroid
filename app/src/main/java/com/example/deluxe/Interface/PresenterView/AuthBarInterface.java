package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.User;

public interface AuthBarInterface {
	interface AuthBarView extends View {
		void setUserInfo(User user);

		void setMoney(double money);
	}

	interface AuthBarPresenter extends Presenter {
	}
}
