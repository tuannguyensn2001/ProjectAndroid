package com.example.deluxe.Interface.PresenterView.History;

import com.example.deluxe.Core.Presenter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public interface LimitInterface {
	interface LimitView extends View, View.ViewUseActionBar {
		void loadView(Class<? extends View> view, Transaction transaction);

		interface StatisticsFragment extends View {
			void getView(HashMap<Date, ArrayList<Transaction>> transactions);
		}
	}

	interface LimitPresenter extends Presenter {
	}
}
