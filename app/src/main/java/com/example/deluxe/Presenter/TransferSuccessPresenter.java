package com.example.deluxe.Presenter;

import com.example.deluxe.Interface.PresenterView.TransferSuccessInterface;
import com.example.deluxe.View.TransferActivity;

public class TransferSuccessPresenter implements TransferSuccessInterface.TransferSuccessPresenter {
	TransferSuccessInterface.TransferSuccessView transferSuccessView;

	public TransferSuccessPresenter(TransferSuccessInterface.TransferSuccessView transferSuccessView) {
		this.transferSuccessView = transferSuccessView;
	}
}
