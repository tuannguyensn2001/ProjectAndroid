package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.User;

public interface TransferInterface {
	interface TransferView extends View {
		void handleButton();

		void handleIsUserCorrect(boolean b);
	}

	interface TransferPresenter extends Presenter {
		void handleTransfer(User user, double money, String message);
	}
}
