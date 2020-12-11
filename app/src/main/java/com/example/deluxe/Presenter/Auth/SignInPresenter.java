package com.example.deluxe.Presenter.Auth;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Interface.Model.AuthLogin;
import com.example.deluxe.Interface.PresenterView.Auth.SignInInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.View.Auth.SignUpActivity;
import com.example.deluxe.View.MainActivity;

public class SignInPresenter implements SignInInterface.SignInPresenter {
	SignInInterface.SignInView loginView;
	UserModel userModel;


	public SignInPresenter(SignInInterface.SignInView Activity) {
		this.loginView = Activity;

		initModel();

		checkAuth();
	}

	public void initModel() {
		this.userModel = new UserModel();
	}

	@Override
	public void handleLogin(String username, String password) {
		User user = new User(username, password);
		Auth.getInstance().attempt(user, new AuthLogin() {
			@Override
			public void loginSuccessful() {
				loginView.loadView(MainActivity.class);
			}

			@Override
			public void loginUnsuccessful() {
				loginView.setNotification(ErrorMessage.ERR110001);
				loginView.handleLoginResult(false);
			}
		});

	}

	public void checkAuth() {
		if (Auth.getInstance().check()) loginView.loadView(MainActivity.class);
	}

	@Override
	public void switchSignUpButton() {
		this.loginView.loadView(SignUpActivity.class);
	}
}