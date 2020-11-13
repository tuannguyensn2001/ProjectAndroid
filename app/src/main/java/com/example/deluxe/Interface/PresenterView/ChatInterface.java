package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.Message;

import java.util.ArrayList;

public interface ChatInterface {

	interface ChatView extends View {

		void setAdapter(ArrayList<Message> list);
	}

	interface ChatPresenter extends Presenter {
		void handleInputMessage(Message message);

		void getListMessage(Message message);
	}

}