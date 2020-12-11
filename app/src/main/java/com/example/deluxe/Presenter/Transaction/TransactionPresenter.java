package com.example.deluxe.Presenter.Transaction;

import com.example.deluxe.Interface.PresenterView.TransactionInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.View.Transaction.DepositActivity;
import com.example.deluxe.View.Auth.SignInActivity;

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
