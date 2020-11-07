package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface LoginInterface {
	interface LoginView extends View {
		void handleClickButton();

		void handleLoginResult(boolean check);
	}

	interface LoginPresenter extends Presenter {
		void handleLogin(String username, String password);

		void switchSignUpButton();
	}
}
