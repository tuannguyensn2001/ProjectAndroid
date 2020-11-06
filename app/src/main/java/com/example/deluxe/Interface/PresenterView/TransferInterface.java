package com.example.deluxe.Interface.PresenterView;

import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.User;

public interface TransferInterface {
	public interface TransferView extends View {
		public void handleButton();
	}

	public interface TransferPresenter {
		public void handleTransfer(User user, double money, String message);
	}
}
