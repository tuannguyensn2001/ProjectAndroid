package com.example.deluxe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Presenter.MainPresenter;
import com.example.deluxe.R;

public class MainActivity extends AppCompatActivity implements MainInterface.MainView {

	private MainInterface.MainPresenter mainPresenter;
	private Button logoutButton, depositButton, transferButton, ruttienButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

		this.logoutButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mainPresenter.handleLogOut();
			}
		});
		this.depositButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mainPresenter.handleDeposit();
			}
		});

		this.transferButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(SearchUserActivity.class);
			}
		});

		this.ruttienButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(WithdrawActivity.class);
			}
		});
	}

	private void init() {
		this.mainPresenter = new MainPresenter(this);
		this.logoutButton = findViewById(R.id.logoutButton);
		this.depositButton = findViewById(R.id.NapThe);
		this.transferButton = findViewById(R.id.transfer);
		this.ruttienButton = findViewById(R.id.ruttien);
	}

	@Override
	public void loadView(Class view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);

	}

	@Override
	public void setNotification(Enum e) {

	}
}
