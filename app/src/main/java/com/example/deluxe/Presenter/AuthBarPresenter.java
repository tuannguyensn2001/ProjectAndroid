package com.example.deluxe.Presenter;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.WalletInterface;
import com.example.deluxe.Interface.PresenterView.AuthBarInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.Model.WalletModel;

public class AuthBarPresenter implements AuthBarInterface.AuthBarPresenter {
	AuthBarInterface.AuthBarView authBarView;
	UserModel userModel;

	public AuthBarPresenter(final AuthBarInterface.AuthBarView authBarView) {
		this.authBarView = authBarView;

		initModel();

		new WalletModel().getMoney(Auth.getInstance().user().getUid(), new WalletInterface() {
			@Override
			public void dataIsLoaded(double money) {
				authBarView.setMoney(money);
			}
		});

		userModel.show(Auth.getInstance().user().getUid(), new com.example.deluxe.Interface.Model.DepositInterface() {
			@Override
			public void dataIsLoaded(User user) {
				authBarView.setUserInfo(user);
			}
		});
	}

	private void initModel() {
		this.userModel = new UserModel();
	}
}
