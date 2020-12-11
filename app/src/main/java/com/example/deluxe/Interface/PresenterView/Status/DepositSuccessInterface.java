package com.example.deluxe.Interface.PresenterView.Status;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;

public interface DepositSuccessInterface {
	interface DepositSuccessView extends View {
		void setAuthBalance(double authBalance);
	}

	interface DepositSuccessPresenter extends Presenter {
	}
}
