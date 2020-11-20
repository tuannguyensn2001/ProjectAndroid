package com.example.deluxe.Presenter.Auth;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Interface.Model.AuthSignUp;
import com.example.deluxe.Interface.PresenterView.Auth.SignUpInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.View.Auth.SignInActivity;

public class SignUpPresenter implements SignUpInterface.SignUpPresenter {
	SignUpInterface.SignUpView signUpView;
	UserModel userModel;

	public SignUpPresenter(SignUpInterface.SignUpView activity) {
		this.signUpView = activity;

		initModel();
	}

	private void initModel() {
		this.userModel = new UserModel();
	}

	@Override
	public void handleSignUp(String username, String password, String email) {
		User user = new User(username, password, email.toLowerCase());
		Auth.getInstance().signUp(user, new AuthSignUp() {
			@Override
			public void signUpSuccessful() {
				signUpView.loadView(SignInActivity.class);
			}

			@Override
			public void signUpUnSuccessful() {
				signUpView.setNotification(ErrorMessage.ERR010002);
			}
		});

	}

	@Override
	public void switchSignInButton() {
		signUpView.loadView(SignInActivity.class);
	}
}
