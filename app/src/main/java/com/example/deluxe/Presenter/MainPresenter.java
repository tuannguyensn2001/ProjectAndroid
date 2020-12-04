package com.example.deluxe.Presenter;

import com.example.deluxe.Interface.Model.WalletInterface;
import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.WalletModel;
import com.example.deluxe.View.Auth.SignInActivity;

public class MainPresenter implements MainInterface.MainPresenter {

	private MainInterface.MainView mainView;

	public MainPresenter(MainInterface.MainView view) {
		this.mainView = view;

		if (!Auth.getInstance().check()) mainView.loadView(SignInActivity.class);

		else {
			new WalletModel().getMoney(Auth.getInstance().user().getUid(), new WalletInterface() {
				@Override
				public void dataIsLoaded(double money) {
					mainView.setMoney(money);
				}
			});
		}
	}

	@Override
	public void handleLogOut() {
		Auth.getInstance().logout();
		mainView.loadView(SignInActivity.class);
	}
}
