package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface SignUpInterface {
	public interface SignUpView extends View {
		void setNotification(Enum e);
	}

	public interface SignUpPresenter extends Presenter {
		public void handleSignUp(String username, String password, String email);
		public void switchSignInButton();
	}
}
