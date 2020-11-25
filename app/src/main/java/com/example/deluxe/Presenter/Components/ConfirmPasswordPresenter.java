package com.example.deluxe.Presenter.Components;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.CheckInterface;
import com.example.deluxe.Interface.Model.UserDetailsInterface;
import com.example.deluxe.Interface.PresenterView.Components.ConfirmPasswordInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;

public class ConfirmPasswordPresenter implements ConfirmPasswordInterface.ConfirmPasswordPresenter {
	ConfirmPasswordInterface.ConfirmPasswordView confirmPasswordView;

	public ConfirmPasswordPresenter(final ConfirmPasswordInterface.ConfirmPasswordView confirmPasswordView) {
		this.confirmPasswordView = confirmPasswordView;

		new UserModel().show(Auth.getInstance().user().getUid(), new UserDetailsInterface() {
			@Override
			public void dataIsLoaded(User user) {
				confirmPasswordView.setUserInfo(user);
			}
		});
	}

	@Override
	public void handleConfirmUser(String passwordInput) {
		new UserModel().checkEmailPassword(new User(null, passwordInput, Auth.getInstance().user().getEmail()), new CheckInterface() {
			@Override
			public void dataIsLoaded(boolean b) {
				confirmPasswordView.handleIsUserCorrect(b);
			}
		});
	}
}
