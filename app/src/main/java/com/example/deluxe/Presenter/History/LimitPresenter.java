package com.example.deluxe.Presenter.History;

import com.example.deluxe.Interface.PresenterView.History.LimitInterface;

public class LimitPresenter implements LimitInterface.LimitPresenter {
	LimitInterface.LimitView limitView;

	public LimitPresenter(LimitInterface.LimitView limitView) {
		this.limitView = limitView;
	}
}
