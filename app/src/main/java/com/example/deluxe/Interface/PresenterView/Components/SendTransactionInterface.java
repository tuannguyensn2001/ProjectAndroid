package com.example.deluxe.Interface.PresenterView.Components;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface SendTransactionInterface {
	interface SendTransactionView extends View, View.ViewUseCheckPasswordDialog {

		void handleIsEnoughMoney(boolean b);

		void handleSendTransaction();
	}

	interface SendTransactionPresenter extends Presenter {

		void checkBalance(double money);

		void handleConfirmUser(String passwordInput);
	}
}
