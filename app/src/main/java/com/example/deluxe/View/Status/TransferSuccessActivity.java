package com.example.deluxe.View.Status;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deluxe.Interface.PresenterView.Status.TransferSuccessInterface;
import com.example.deluxe.Presenter.Status.TransferSuccessPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.MainActivity;
import com.example.deluxe.View.Transaction.TransferSearchActivity;

public class TransferSuccessActivity extends AppCompatActivity implements TransferSuccessInterface.TransferSuccessView {
	TransferSuccessInterface.TransferSuccessPresenter transferSuccessPresenter;
	TextView homeButton, continueTransferButton;
	TextView username, money, message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transfer_success);

		init();

		homeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(MainActivity.class);
			}
		});

		continueTransferButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(TransferSearchActivity.class);
			}
		});
	}

	private void init() {
		username = findViewById(R.id.username);
		money = findViewById(R.id.money);
		message = findViewById(R.id.message);

		homeButton = findViewById(R.id.home_button);
		continueTransferButton = findViewById(R.id.continue_transfer_button);

		transferSuccessPresenter = new TransferSuccessPresenter(this);

		//		Lay ten nguoi dung va email
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			username.setText(bundle.getString("Username", ""));
			money.setText(bundle.getString("Money", ""));
			message.setText(bundle.getString("Message", ""));
		}
	}


	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
		finish();
	}

	@Override
	public void setNotification(Enum e) {

	}
}