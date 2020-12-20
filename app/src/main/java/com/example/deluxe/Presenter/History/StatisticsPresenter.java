package com.example.deluxe.Presenter.History;

import com.example.deluxe.Entity.Transaction;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.Model.ListTransactionInterface;
import com.example.deluxe.Interface.PresenterView.History.StatisticsInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Model.HistoryModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class StatisticsPresenter implements StatisticsInterface.StatisticsPresenter {
	StatisticsInterface.StatisticsView.StatisticsFragment statisticsFragment;

	public StatisticsPresenter(StatisticsInterface.StatisticsView.StatisticsFragment statisticsFragment) {
		this.statisticsFragment = statisticsFragment;
	}

	@Override
	public void getTransactionArray(int pos) {
		switch (pos) {
			case 0:
				new HistoryModel().getListDeposit(new User(null, null, Auth.getInstance().user().getEmail()), new ListTransactionInterface() {
					@Override
					public void dataIsLoaded(HashMap<Date, ArrayList<Transaction>> transactions) {
						statisticsFragment.getView(transactions);
					}
				});
				break;
			case 1:
				new HistoryModel().getListWithdraw(new User(null, null, Auth.getInstance().user().getEmail()), new ListTransactionInterface() {
					@Override
					public void dataIsLoaded(HashMap<Date, ArrayList<Transaction>> transactions) {
						statisticsFragment.getView(transactions);
					}
				});
				break;
			case 2:
				new HistoryModel().getListTransfer(new User(null, null, Auth.getInstance().user().getEmail()), new ListTransactionInterface() {
					@Override
					public void dataIsLoaded(HashMap<Date, ArrayList<Transaction>> transactions) {
						statisticsFragment.getView(transactions);
					}
				});
				break;
			case 3:
				new HistoryModel().getDetailOrder(Auth.getInstance().user().getUid(), new ListTransactionInterface() {
					@Override
					public void dataIsLoaded(HashMap<Date, ArrayList<Transaction>> transactions) {
						statisticsFragment.getView(transactions);
					}
				});
				break;
			case 4:
//				new StatisticModel().getPerMonth(Auth.getInstance().user().getUid(), new ListGetPerMonth() {
//					@Override
//					public void dataIsLoaded(List<Transaction> list) {
//						HashMap<Date, ArrayList<Transaction>> hashMap = new HashMap<>();
//						hashMap.put(new Date(), (ArrayList<Transaction>) list);
//						statisticsFragment.getView(hashMap);
//					}
//				});
				new HistoryModel().getListDeposit(new User(null, null, Auth.getInstance().user().getEmail()), new ListTransactionInterface() {
					@Override
					public void dataIsLoaded(HashMap<Date, ArrayList<Transaction>> transactions) {
						statisticsFragment.getView(transactions);
					}
				});
				break;
			case 5:
//				new StatisticModel().getLimit(Auth.getInstance().user().getUid(), new ListGetPerMonth() {
//					@Override
//					public void dataIsLoaded(List<Transaction> list) {
//						HashMap<Date, ArrayList<Transaction>> hashMap = new HashMap<>();
//						hashMap.put(new Date(), (ArrayList<Transaction>) list);
//						statisticsFragment.getView(hashMap);
//					}
//				});
				new HistoryModel().getListDeposit(new User(null, null, Auth.getInstance().user().getEmail()), new ListTransactionInterface() {
					@Override
					public void dataIsLoaded(HashMap<Date, ArrayList<Transaction>> transactions) {
						statisticsFragment.getView(transactions);
					}
				});
				break;
			default:
				break;
		}

	}
}
