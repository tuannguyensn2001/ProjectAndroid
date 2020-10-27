package com.example.deluxe.Presenter;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.AuthSignUp;
import com.example.deluxe.Interface.PresenterView.LoginInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.View.SignUpActivity;

public class LoginPresenter implements LoginInterface.LoginPresenter {
	LoginInterface.LoginView loginView;
	UserModel userModel;


	public LoginPresenter(LoginInterface.LoginView Activity) {
		this.loginView = Activity;

		initModel();
	}

	public void initModel() {
		this.userModel = new UserModel();
	}

	@Override
	public void handleLogin(String username, String password) {
		User user = new User(username, password);
		Auth.getInstance().signUp(user, new AuthSignUp() {
			@Override
			public void signUpSuccessful() {

			}

			@Override
			public void signUpunSuccessful() {

			}
		});
		this.loginView.handleLoginResult(true);

	}

	@Override
	public void switchSignUpButton() {
		this.loginView.loadView(SignUpActivity.class);
	}
}