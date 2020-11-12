package com.example.deluxe.Presenter;

import com.example.deluxe.Interface.PresenterView.TransactionInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.View.DepositActivity;
import com.example.deluxe.View.SignInActivity;
import com.example.deluxe.View.TransactionFragment;

public class TransactionPresenter implements TransactionInterface.TransactionPresenter {
	TransactionInterface.TransactionView transactionView;

	public TransactionPresenter(TransactionInterface.TransactionView transactionView) {
		this.transactionView = transactionView;

		if (!Auth.getInstance().check()) transactionView.loadView(SignInActivity.class);
	}

	@Override
	public void handleLogOut() {
		Auth.getInstance().logout();
		transactionView.loadView(SignInActivity.class);
	}

	@Override
	public void handleDeposit() {
		transactionView.loadView(DepositActivity.class);
	}
}
