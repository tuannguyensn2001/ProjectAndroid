package com.example.deluxe.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Presenter.MainPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.Account.AccountFragment;
import com.example.deluxe.View.Chat.ChatUserFragment;
import com.example.deluxe.View.History.HistoryFragment;
import com.example.deluxe.View.Transaction.TransactionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements MainInterface.MainView {
	MainInterface.MainPresenter mainPresenter;

	ImageView signOutButton;
	BottomNavigationView bottomNav;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

		signOutButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mainPresenter.handleLogOut();
			}
		});

		bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				Fragment selectedFragment = null;
				switch (item.getItemId()) {
					case R.id.nav_transaction:
						selectedFragment = new TransactionFragment();
						break;
					case R.id.nav_history:
						selectedFragment = new HistoryFragment();
						break;
					case R.id.nav_chat:
						selectedFragment = new ChatUserFragment();
						break;
					case R.id.nav_account:
						selectedFragment = new AccountFragment();
						break;
				}
				assert selectedFragment != null;
				getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment,
						selectedFragment).commit();
				return true;
			}
		});

		getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment,
				new TransactionFragment()).commit();
	}

	private void init() {
		this.mainPresenter = new MainPresenter(this);

		this.signOutButton = findViewById(R.id.sign_out_button);
		this.bottomNav = findViewById(R.id.bottom_navigation);
	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}

	@Override
	public void setMoney(double money) {
		((TextView) findViewById(R.id.auth_balance)).setText(new DecimalFormat("#,###,###").format(money));
	}
}