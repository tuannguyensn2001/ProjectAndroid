package com.example.deluxe.Presenter.Transaction;

import com.example.deluxe.Interface.PresenterView.Transaction.TransactionInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.View.Auth.SignInActivity;

public class TransactionPresenter implements TransactionInterface.TransactionPresenter {
	TransactionInterface.TransactionView transactionView;

	public TransactionPresenter(TransactionInterface.TransactionView transactionView) {
		this.transactionView = transactionView;

		if (!Auth.getInstance().check()) transactionView.loadView(SignInActivity.class);
	}
}
