package com.example.deluxe.Interface.PresenterView.Components;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Entity.User;

import java.util.ArrayList;

public interface SearchUserInterface {

	interface SearchUserView {
		void setList(ArrayList<User> list);
	}

	interface SearchUserPresenter extends Presenter {
		void handleInput(String emailSearch);
	}
}
