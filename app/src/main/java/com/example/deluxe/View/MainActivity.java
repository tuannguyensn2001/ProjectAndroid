package com.example.deluxe.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.R;
import com.example.deluxe.View.Account.AccountFragment;
import com.example.deluxe.View.Chat.ChatUserFragment;
import com.example.deluxe.View.History.HistoryFragment;
import com.example.deluxe.View.Transaction.TransactionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements MainInterface.MainView {
	BottomNavigationView bottomNav;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

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
		this.bottomNav = findViewById(R.id.bottom_navigation);
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