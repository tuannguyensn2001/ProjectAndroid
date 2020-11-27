package com.example.deluxe.Presenter.Transaction;

import com.example.deluxe.Interface.PresenterView.Transaction.TransferSearchInterface;

public class TransferSearchPresenter implements TransferSearchInterface.TransferSearchPresenter {
	TransferSearchInterface.TransferSearchView transferSearchView;

	public TransferSearchPresenter(TransferSearchInterface.TransferSearchView transferSearchView) {
		this.transferSearchView = transferSearchView;
	}
}
