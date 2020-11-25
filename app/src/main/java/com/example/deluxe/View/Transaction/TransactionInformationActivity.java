package com.example.deluxe.View.Transaction;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deluxe.Core.View;
import com.example.deluxe.Interface.PresenterView.Transaction.TransactionInformationInterface;
import com.example.deluxe.Presenter.Transaction.TransactionInformationPresenter;
import com.example.deluxe.R;

public class TransactionInformationActivity extends AppCompatActivity implements TransactionInformationInterface.TransactionInformationView {
	TransactionInformationInterface.TransactionInformationPresenter transactionInformationPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_information);

		init();
	}

	private void init() {
		this.transactionInformationPresenter = new TransactionInformationPresenter(this);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.statistics_action_bar_title));
	}

	@Override
	public void loadView(Class<? extends View> view) {

	}

	@Override
	public void setNotification(Enum e) {

	}
}