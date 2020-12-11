package com.example.deluxe.Interface.PresenterView.Auth;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface SignInInterface {
	interface SignInView extends View {
		void handleClickButton();

		void handleLoginResult(boolean check);
	}

	interface SignInPresenter extends Presenter {
		void handleLogin(String username, String password);

		void switchSignUpButton();
	}
}
