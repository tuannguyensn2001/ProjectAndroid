package com.example.deluxe.Presenter;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.PresenterView.TransferInterface;

public class TransferPresenter implements TransferInterface.TransferPresenter {
	TransferInterface.TransferView transferView;

	public TransferPresenter(TransferInterface.TransferView transferView) {
		this.transferView = transferView;
	}

	@Override
	public void handleTransfer(User user, double money, String message) {
		//TODO lam xong phan chuyen tien
	}
}
