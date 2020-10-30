package com.example.deluxe.Presenter;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.AuthLogin;
import com.example.deluxe.Interface.Model.AuthSignUp;
import com.example.deluxe.Interface.PresenterView.LoginInterface;
import com.example.deluxe.Interface.PresenterView.SignUpInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.View.LoginActivity;

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
		User user = new User(username, password, email);
		Auth.getInstance().signUp(user, new AuthSignUp() {
			@Override
			public void signUpSuccessful() {
				signUpView.loadView(LoginActivity.class);
			}

			@Override
			public void signUpunSuccessful() {
				signUpView.setError("That bai!");
			}
		});

	}

	@Override
	public void switchSignInButton() {
		signUpView.loadView(LoginActivity.class);
	}
}
