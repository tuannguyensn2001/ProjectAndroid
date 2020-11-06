package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface MainInterface extends Presenter {
	interface MainPresenter {
		void handleLogOut();

		void handleDeposit();
	}

	interface MainView extends View {
	}
}