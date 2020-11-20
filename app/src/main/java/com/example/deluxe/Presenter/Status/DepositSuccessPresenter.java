package com.example.deluxe.Presenter.Status;

import com.example.deluxe.Interface.Model.WalletInterface;
import com.example.deluxe.Interface.PresenterView.Status.DepositSuccessInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.WalletModel;

public class DepositSuccessPresenter implements DepositSuccessInterface.DepositSuccessPresenter {
	DepositSuccessInterface.DepositSuccessView depositSuccessView;

	public DepositSuccessPresenter(final DepositSuccessInterface.DepositSuccessView depositSuccessView) {
		this.depositSuccessView = depositSuccessView;

		new WalletModel().getMoney(Auth.getInstance().user().getUid(), new WalletInterface() {
			@Override
			public void dataIsLoaded(double money) {
				depositSuccessView.setAuthBalance(money);
			}
		});
	}
}
