package com.example.deluxe.Presenter.Transaction;

import com.example.deluxe.Entity.Transfer;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Interface.Model.TransferFirebase;
import com.example.deluxe.Interface.Model.WalletInterface;
import com.example.deluxe.Interface.PresenterView.Transaction.TransferInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.TransferModel;
import com.example.deluxe.Model.WalletModel;
import com.example.deluxe.View.Status.TransferSuccessActivity;

public class TransferPresenter implements TransferInterface.TransferPresenter {
	TransferInterface.TransferView transferView;

	public TransferPresenter(TransferInterface.TransferView transferView) {
		this.transferView = transferView;
	}

	@Override
	public void handleTransfer(final User user, final double money, final String message) {
		new WalletModel().getMoneyOnce(Auth.getInstance().user().getUid(), new WalletInterface() {
			@Override
			public void dataIsLoaded(double money_now) {
				if (money > money_now) {
					transferView.setNotification(ErrorMessage.ERR310000);
				} else {
					final Transfer transfer = new Transfer(Auth.getInstance().user().getEmail(), user.getEmail(), money, message);
					(new TransferModel()).transfer(transfer, new TransferFirebase() {
						@Override
						public void success(SuccessMessage successMessage) {
							transferView.loadView(TransferSuccessActivity.class);
						}

						@Override
						public void failed(ErrorMessage errorMessage) {
							transferView.setNotification(errorMessage);
						}
					});
				}
			}
		});

	}

	@Override
	public void checkBalance(final double money) {
		new WalletModel().getMoneyOnce(Auth.getInstance().user().getUid(), new WalletInterface() {
			@Override
			public void dataIsLoaded(double money_now) {
				if (money > money_now) {
					transferView.setNotification(ErrorMessage.ERR310000);
				} else {
					transferView.handleDialog();
				}
			}
		});
	}
}
