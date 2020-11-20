package com.example.deluxe.Interface.PresenterView.Transaction;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Model.Auth;
import com.google.firebase.auth.FirebaseUser;

public interface WithdrawInterface {
	interface WithdrawView extends View, View.ViewUseActionBar, View.ViewUseCheckPasswordDialog {
	}

	interface WithDrawPresenter extends Presenter {
		void handleWithdraw(double money, String note);

		void handleConfirmUser(String passwordInput);
	}
}
