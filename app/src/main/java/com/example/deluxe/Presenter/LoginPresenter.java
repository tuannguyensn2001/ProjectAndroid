package com.example.deluxe.Presenter;

import android.util.Log;

import com.example.deluxe.Entity.Card;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Entity.Wallet;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Interface.Model.AuthLogin;
import com.example.deluxe.Interface.Model.AuthSignUp;
import com.example.deluxe.Interface.Model.CardInterface;
import com.example.deluxe.Interface.PresenterView.LoginInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.CardModel;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.Model.WalletModel;
import com.example.deluxe.View.MainActivity;
import com.example.deluxe.View.SignUpActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LoginPresenter implements LoginInterface.LoginPresenter {
	LoginInterface.LoginView loginView;
	UserModel userModel;


	public LoginPresenter(LoginInterface.LoginView Activity) {
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