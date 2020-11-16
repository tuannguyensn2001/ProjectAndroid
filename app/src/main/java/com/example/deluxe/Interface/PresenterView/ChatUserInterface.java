package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.LastMessage;
import com.example.deluxe.Interface.Model.MessageInterface;

import java.util.ArrayList;

public interface ChatUserInterface {

	interface ChatUserPresenter extends Presenter {
		void showData();

		void getItem(int position);
	}

	interface ChatUserView extends View {
		void setAdapter(ArrayList<LastMessage> list);

		void loadView(String email, Class view);

	}

}