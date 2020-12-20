package com.example.deluxe.Interface.PresenterView.Auth;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface PasswordChangeInterface {
	interface PasswordChangeView extends View {

		void setSubmitable(boolean can);
	}

	interface PasswordChangePresenter extends Presenter {

		void handlePassChange(String currentPassword, String newPassword, String confirmPassword);
	}
}
