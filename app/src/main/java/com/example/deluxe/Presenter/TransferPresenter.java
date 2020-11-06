package com.example.deluxe.Presenter;

import com.example.deluxe.Entity.Transfer;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Interface.Model.WalletInterface;
import com.example.deluxe.Interface.PresenterView.TransferInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.TransferModel;
import com.example.deluxe.Model.WalletModel;

public class TransferPresenter implements TransferInterface.TransferPresenter {
	TransferInterface.TransferView transferView;

	public TransferPresenter(TransferInterface.TransferView transferView) {
		this.transferView = transferView;
	}

	@Override
	public void handleTransfer(final User user, final double money, final String message) {
		//TODO lam xong phan chuyen tien;


		new WalletModel().getMoneyOnce(Auth.getInstance().user().getUid(), new WalletInterface() {
			@Override
			public void dataIsLoaded(double money_now) {
				if (money > money_now) {
					transferView.setNotification(ErrorMessage.ERR310000);
				} else {
					final Transfer transfer = new Transfer(Auth.getInstance().user().getEmail(), user.getEmail(), money, message);
					(new TransferModel()).transfer(transfer);
				}
			}
		});

	}
}
