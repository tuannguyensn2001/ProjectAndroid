package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.User;

import java.util.ArrayList;

public interface SearchUserInterface {

	interface SearchUserView extends View {
		void setList(ArrayList<User> users);
	}

	interface SearchUserPresenter extends Presenter {
		void handleInput(String emailSearch);
	}
}
