package com.example.deluxe.Presenter.History;

import com.example.deluxe.Interface.PresenterView.History.HistoryInterface;
import com.example.deluxe.View.History.HistoryFragment;

public class HistoryPresenter implements HistoryInterface.HistoryPresenter {
	HistoryFragment historyFragment;

	public HistoryPresenter(HistoryFragment historyFragment) {
		this.historyFragment = historyFragment;
	}
}
