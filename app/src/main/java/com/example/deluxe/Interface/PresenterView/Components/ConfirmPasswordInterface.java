package com.example.deluxe.Interface.PresenterView.Components;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.User;

public interface ConfirmPasswordInterface {
	interface ConfirmPasswordView extends View {
		void handleIsUserCorrect(boolean b);

		void setUserInfo(User user);
	}

	interface ConfirmPasswordPresenter extends Presenter {
		void handleConfirmUser(String passwordInput);
	}
}
