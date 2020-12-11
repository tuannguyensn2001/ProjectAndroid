package com.example.deluxe.Interface.PresenterView.Auth;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface SignUpInterface {
	interface SignUpView extends View {
	}

	interface SignUpPresenter extends Presenter {
		void handleSignUp(String username, String password, String email);

		void switchSignInButton();
	}
}
