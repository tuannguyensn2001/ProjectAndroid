package com.example.deluxe.Interface.PresenterView.Transaction;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface WithdrawInterface {
	interface WithdrawView extends View, View.ViewUseActionBar, View.ViewUseCheckPasswordDialog {
		void handleDialog();
	}

	interface WithDrawPresenter extends Presenter {
		void handleWithdraw(double money, String note);

		void checkBalance(double money);
	}
}
