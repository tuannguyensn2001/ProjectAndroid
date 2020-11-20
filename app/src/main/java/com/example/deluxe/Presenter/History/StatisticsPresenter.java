package com.example.deluxe.Presenter.History;

import com.example.deluxe.Interface.PresenterView.History.StatisticsInterface;

public class StatisticsPresenter implements StatisticsInterface.StatisticsPresenter {
	StatisticsInterface.StatisticsView statisticsView;

	public StatisticsPresenter(StatisticsInterface.StatisticsView statisticsView) {
		this.statisticsView = statisticsView;
	}
}
