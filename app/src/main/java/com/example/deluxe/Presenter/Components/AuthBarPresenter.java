package com.example.deluxe.Presenter.Components;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.UserDetailsInterface;
import com.example.deluxe.Interface.Model.WalletInterface;
import com.example.deluxe.Interface.PresenterView.Components.AuthBarInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.Model.WalletModel;

public class AuthBarPresenter implements AuthBarInterface.AuthBarPresenter {
	AuthBarInterface.AuthBarView authBarView;
	UserModel userModel;

	public AuthBarPresenter(final AuthBarInterface.AuthBarView authBarView) {
		this.authBarView = authBarView;

		initModel();

		userModel.show(Auth.getInstance().user().getUid(), new UserDetailsInterface() {
			@Override
			public void dataIsLoaded(User user) {
				authBarView.setUserInfo(user);
			}
		});

		new WalletModel().getMoney(Auth.getInstance().user().getUid(), new WalletInterface() {
			@Override
			public void dataIsLoaded(double money) {
				authBarView.setMoney(money);
			}
		});
	}

	private void initModel() {
		this.userModel = new UserModel();
	}
}
