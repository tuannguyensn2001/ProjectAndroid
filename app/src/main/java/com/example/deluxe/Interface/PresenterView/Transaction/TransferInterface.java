package com.example.deluxe.Interface.PresenterView.Transaction;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.User;

public interface TransferInterface {
	interface TransferView extends View, View.ViewUseActionBar, View.ViewUseCheckPasswordDialog {
		void handleButton();

		void handleDialog();
	}

	interface TransferPresenter extends Presenter {
		void handleTransfer(User user, double money, String message);

		void checkBalance(double money);
	}
}
