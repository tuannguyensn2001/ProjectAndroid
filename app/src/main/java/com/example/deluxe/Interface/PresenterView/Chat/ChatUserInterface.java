package com.example.deluxe.Interface.PresenterView.Chat;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.LastMessage;

import java.util.ArrayList;

public interface ChatUserInterface {

	interface ChatUserPresenter extends Presenter {
		void showData();

		void getItem(int position);
	}

	interface ChatUserView extends View, View.ViewUseSearchBar {
		void setAdapter(ArrayList<LastMessage> list);

		void handleIsHaveMessage(boolean isHaveMessage);
	}

}