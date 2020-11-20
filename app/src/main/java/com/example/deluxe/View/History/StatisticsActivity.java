package com.example.deluxe.View.History;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Adapter.StatisticsCard.StatisticsCardChild;
import com.example.deluxe.Adapter.StatisticsCard.StatisticsCardDad;
import com.example.deluxe.Adapter.StatisticsCard.StatisticsCardRecyclerAdapter;
import com.example.deluxe.Entity.Transaction;
import com.example.deluxe.Enum.TransactionType;
import com.example.deluxe.Interface.PresenterView.History.StatisticsInterface;
import com.example.deluxe.Presenter.History.StatisticsPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.MainActivity;

import java.util.ArrayList;
import java.util.Date;

public class StatisticsActivity extends AppCompatActivity implements StatisticsInterface.StatisticsView {
	StatisticsInterface.StatisticsPresenter statisticsPresenter;

	RecyclerView transactionRecyclerList;
	private ArrayList<StatisticsCardDad> transactionDads;
	private StatisticsCardRecyclerAdapter recyclerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);

		init();

		initData();

		LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		transactionRecyclerList.setLayoutManager(layoutManager);

		recyclerAdapter = new StatisticsCardRecyclerAdapter(transactionDads);
		transactionRecyclerList.setAdapter(recyclerAdapter);
	}

	private void initData() {
		transactionDads = new ArrayList<>();

		ArrayList<StatisticsCardChild> children0 = new ArrayList<>();
		children0.add(new StatisticsCardChild(new Transaction(TransactionType.DEPOSIT, 1000000, new Date(), true, null, null)));
		children0.add(new StatisticsCardChild(new Transaction(TransactionType.WITHDRAW, 1000000, new Date(), true, null, "Chuc be ngu ngon")));
		children0.add(new StatisticsCardChild(new Transaction(TransactionType.WITHDRAW, 1000000, new Date(), false, null, "Chuc be ngu ngon")));
		children0.add(new StatisticsCardChild(new Transaction(TransactionType.USE, 1000000, new Date(), true, null, "Chuc be ngu ngon")));
		children0.add(new StatisticsCardChild(new Transaction(TransactionType.RECEIVE, 1000000, new Date(), true, "caythongotrennui@gmail.com", "Chuc be ngu ngon")));
		children0.add(new StatisticsCardChild(new Transaction(TransactionType.DEPOSIT, 1000000, new Date(), true, null, null)));
		children0.add(new StatisticsCardChild(new Transaction(TransactionType.DEPOSIT, 1000000, new Date(), true, null, null)));

		transactionDads.add(new StatisticsCardDad("Thang 1", children0));
		transactionDads.add(new StatisticsCardDad("Thang 3", children0));
		transactionDads.add(new StatisticsCardDad("Thang 4", children0));
		transactionDads.add(new StatisticsCardDad("Thang 8", children0));
	}

	private void init() {
		this.statisticsPresenter = new StatisticsPresenter(this);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.statistics_action_bar_title));

		transactionRecyclerList = findViewById(R.id.transaction_list);
	}

	@Override
	public void loadView(Class view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}

	@Override
	public void handleBackButton() {
		loadView(MainActivity.class);
	}
}