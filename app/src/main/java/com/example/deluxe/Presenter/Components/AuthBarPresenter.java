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

<<<<<<< HEAD:app/src/main/java/com/example/deluxe/Presenter/Components/AuthBarPresenter.java
		new WalletModel().getMoney(Auth.getInstance().user().getUid(), new WalletInterface() {
=======
		userModel.show(Auth.getInstance().user().getUid(), new com.example.deluxe.Interface.Model.UserDetailsInterface() {
>>>>>>> 6e52742c5d4d535db56eda368b46d114b2de4a74:app/src/main/java/com/example/deluxe/Presenter/AuthBarPresenter.java
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
