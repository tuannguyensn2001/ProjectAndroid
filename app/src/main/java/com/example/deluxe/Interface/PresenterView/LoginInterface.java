package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.User;

import java.util.ArrayList;

public interface LoginInterface {
	public interface LoginView extends View {
		void handleClickButton();
		void setNotification(Enum e);
		void handleLoginResult(boolean check);
	}

	public interface LoginPresenter extends Presenter {
		void handleLogin(String username, String password);
		void switchSignUpButton();
	}
}
