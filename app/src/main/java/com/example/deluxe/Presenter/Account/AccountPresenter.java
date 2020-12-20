package com.example.deluxe.Presenter.Account;

import android.net.Uri;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.UserDetailsInterface;
import com.example.deluxe.Interface.PresenterView.Account.AccountInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;

public class AccountPresenter implements AccountInterface.AccountPresenter {
	private AccountInterface.AccountView accountView;

	public AccountPresenter(final AccountInterface.AccountView accountView) {
		this.accountView = accountView;

		new UserModel().show(Auth.getInstance().user().getUid(), new UserDetailsInterface() {
			@Override
			public void dataIsLoaded(User user) {
				accountView.setUserInfo(user);
			}
		});
	}


	@Override
	public void getAvatar() {
		new UserModel().show(Auth.getInstance().user().getUid(), new UserDetailsInterface() {
			@Override
			public void dataIsLoaded(User user) {
				accountView.setAvatar(user.getAvatar());
			}
		});
	}

	@Override
	public void receiveAvatar(Uri filePath) {
		new UserModel().uploadAvatar(filePath);
	}
}