package com.example.deluxe.Presenter;

import android.view.View;

import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.LoginInterface;
import com.example.deluxe.View.MainActivity;

public class Login implements LoginInterface.LoginPresenter {
	LoginInterface.LoginView loginView;


	public Login(LoginInterface.LoginView Activity) {
		this.loginView = Activity;
	}


	@Override
	public void handleLogin(String username, String password) {
		if (!Rules.minLength(username, 6) || !Rules.minLength(password, 6))
			this.loginView.setError("Sai roi");
	}
}
