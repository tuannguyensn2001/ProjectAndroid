package com.example.deluxe.Core;

import com.example.deluxe.Entity.Message;
import com.example.deluxe.Entity.User;

import java.util.ArrayList;

public interface View {
	void loadView(Class<? extends View> view);

	void setNotification(Enum e);

	interface ViewUseActionBar {
	}

	interface ViewUseInformationActionBar {
		void setReceiverInformation(User user);
	}

	interface ViewUseSearchBar {
		void setList(ArrayList<User> users);

		void handleOnItemClick(User user);

		void handleIsAlreadySearch(boolean isAlreadySearch);
	}

	interface ViewUseCheckPasswordDialog {
		void handleIsUserCorrect(boolean b);
	}

	interface ViewUseSendTransaction extends ViewUseCheckPasswordDialog {
		void sendTransaction(boolean isTransfer, double moneyInputNumber, String messageInput);

		void updateTransaction(Message message);
	}
}
