package com.example.deluxe.Presenter.Status;

import com.example.deluxe.Interface.PresenterView.TransferSuccessInterface;

public class TransferSuccessPresenter implements TransferSuccessInterface.TransferSuccessPresenter {
	TransferSuccessInterface.TransferSuccessView transferSuccessView;

	public TransferSuccessPresenter(TransferSuccessInterface.TransferSuccessView transferSuccessView) {
		this.transferSuccessView = transferSuccessView;
	}
}
