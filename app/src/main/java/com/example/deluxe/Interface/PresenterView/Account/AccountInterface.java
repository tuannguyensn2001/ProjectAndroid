package com.example.deluxe.Interface.PresenterView.Account;

import android.net.Uri;

public interface AccountInterface {

	interface AccountPresenter {
		void getAvatar();

		void receiveAvatar(Uri filePath);

	}

	interface AccountView {
		void setAvatar(String url);
	}
}