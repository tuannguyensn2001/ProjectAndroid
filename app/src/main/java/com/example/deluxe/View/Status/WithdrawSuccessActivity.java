package com.example.deluxe.View.Status;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deluxe.Core.View;
import com.example.deluxe.R;
import com.example.deluxe.View.MainActivity;
import com.example.deluxe.View.Transaction.WithdrawActivity;

public class WithdrawSuccessActivity extends AppCompatActivity implements View {
	TextView homeButton, continueWithdrawButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdraw_success);

		init();

		homeButton.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				loadView(MainActivity.class);
			}
		});

		continueWithdrawButton.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				loadView(WithdrawActivity.class);
			}
		});
	}

	private void init() {
		homeButton = findViewById(R.id.home_button);
		continueWithdrawButton = findViewById(R.id.continue_withdraw_button);
	}

	@Override
	public void loadView(Class<? extends View> view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
		finish();
	}

	@Override
	public void setNotification(Enum e) {

	}
}