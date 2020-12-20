package com.example.deluxe.Interface.PresenterView.Account;

import android.net.Uri;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface AccountInterface {

	interface AccountPresenter extends Presenter {
		void getAvatar();

		void receiveAvatar(Uri filePath);

	}

	interface AccountView extends View {
		void setAvatar(String url);
	}
}