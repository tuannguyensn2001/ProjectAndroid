package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface MainInterface {
	interface MainPresenter extends Presenter {
		void handleLogOut();
	}

	interface MainView extends View {
		void setMoney(double money);
	}
}