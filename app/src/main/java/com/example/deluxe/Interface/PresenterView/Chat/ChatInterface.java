package com.example.deluxe.Interface.PresenterView.Chat;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.Message;
import com.example.deluxe.Entity.User;

import java.util.ArrayList;

public interface ChatInterface {

	interface ChatView extends View, View.ViewUseInformationActionBar, View.ViewUseSendTransaction {
		void handleSendTransactionButton();

		void handleDialog();

		void setAdapter(ArrayList<Message> list);
	}

	interface ChatPresenter extends Presenter {
		void handleInputMessage(Message message);

		void getListMessage(Message message);

		void getReceiverInformation(String emailReceiver);

		void handleTransfer(final User user, final double money, final String message);

		void updateTransaction(Message message);
	}

}