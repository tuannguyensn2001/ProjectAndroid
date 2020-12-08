package com.example.deluxe.Presenter.Components;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Interface.Model.CheckInterface;
import com.example.deluxe.Interface.Model.WalletInterface;
import com.example.deluxe.Interface.PresenterView.Components.SendTransactionInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.Model.WalletModel;

public class SendTransactionPresenter implements SendTransactionInterface.SendTransactionPresenter {
	SendTransactionInterface.SendTransactionView sendTransactionView;

	public SendTransactionPresenter(SendTransactionInterface.SendTransactionView sendTransactionView) {
		this.sendTransactionView = sendTransactionView;
	}

	@Override
	public void checkBalance(final double money) {
		new WalletModel().getMoneyOnce(Auth.getInstance().user().getUid(), new WalletInterface() {
			@Override
			public void dataIsLoaded(double money_now) {
				if (money > money_now) {
					sendTransactionView.setNotification(ErrorMessage.ERR310000);
				} else {
					sendTransactionView.handleIsEnoughMoney(true);
				}
			}
		});
	}

	@Override
	public void handleConfirmUser(String passwordInput) {
		new UserModel().checkEmailPassword(new User(null, passwordInput, Auth.getInstance().user().getEmail()), new CheckInterface() {
			@Override
			public void dataIsLoaded(boolean b) {
				if (b)
					sendTransactionView.handleSendTransaction();
				else
					sendTransactionView.setNotification(ErrorMessage.ERR500003);
			}
		});
	}
}
