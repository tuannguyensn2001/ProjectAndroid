package com.example.deluxe.Presenter.Auth;

import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Interface.Model.ChangePasswordInterface;
import com.example.deluxe.Interface.PresenterView.Auth.PasswordChangeInterface;
import com.example.deluxe.Model.UserModel;
import com.example.deluxe.View.MainActivity;

public class PasswordChangePresenter implements PasswordChangeInterface.PasswordChangePresenter {
	PasswordChangeInterface.PasswordChangeView passwordChangeView;
	UserModel userModel;

	public PasswordChangePresenter(PasswordChangeInterface.PasswordChangeView passwordChangeView) {
		this.passwordChangeView = passwordChangeView;
	}

	@Override
	public void handlePassChange(String currentPassword, String newPassword, String confirmPassword) {

		new UserModel().updatePassword(currentPassword, newPassword, confirmPassword, new ChangePasswordInterface() {
			@Override
			public void success() {
				passwordChangeView.loadView(MainActivity.class);
			}

			@Override
			public void failedPassword() {
				passwordChangeView.setNotification(ErrorMessage.ERR500003);
			}

			@Override
			public void failedUpdate(Exception ex) {
				passwordChangeView.setNotification(ErrorMessage.ERR710001);
				passwordChangeView.setSubmitable(true);
			}
		});
	}
}
