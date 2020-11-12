package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface TransactionInterface {
	interface TransactionView extends View {
	}

	interface TransactionPresenter extends Presenter {
		void handleLogOut();

		void handleDeposit();
	}
}
