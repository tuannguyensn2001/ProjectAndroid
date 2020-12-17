package com.example.deluxe.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Helper.ConvertData;
import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Presenter.MainPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.Account.AccountFragment;
import com.example.deluxe.View.Chat.ChatUserFragment;
import com.example.deluxe.View.History.HistoryFragment;
import com.example.deluxe.View.Transaction.TransactionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements MainInterface.MainView {
	MainInterface.MainPresenter mainPresenter;

	ImageView signOutButton;
	BottomNavigationView bottomNav;
	ConstraintLayout appBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		transparentStatusAndNavigation(this);

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
						selectedFragment = TransactionFragment.newInstance();
						break;
					case R.id.nav_history:
						selectedFragment = HistoryFragment.newInstance();
						break;
					case R.id.nav_chat:
						selectedFragment = ChatUserFragment.newInstance();
						break;
					case R.id.nav_account:
						selectedFragment = AccountFragment.newInstance();
						break;
				}
				assert selectedFragment != null;
				getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment,
						selectedFragment).commit();

				switch (item.getItemId()) {
					case R.id.nav_transaction:
					case R.id.nav_history:
					case R.id.nav_account:
						findViewById(R.id.app_bar).setVisibility(View.VISIBLE);
						break;
					case R.id.nav_chat:
						findViewById(R.id.app_bar).setVisibility(View.GONE);
						break;
				}
				return true;
			}
		});

		getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment,
				new TransactionFragment()).commit();
	}

	private void transparentStatusAndNavigation(Activity activity) {
		Window win = activity.getWindow();

		win.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
		WindowManager.LayoutParams winParams = win.getAttributes();
		winParams.flags &= ~(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
				| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		win.setAttributes(winParams);

		win.setStatusBarColor(Color.TRANSPARENT);
		win.setNavigationBarColor(Color.TRANSPARENT);
	}

	private void init() {
		this.mainPresenter = new MainPresenter(this);

		this.signOutButton = findViewById(R.id.sign_out_button);
		this.bottomNav = findViewById(R.id.bottom_navigation);
		this.appBar = findViewById(R.id.app_bar);
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
		((TextView) findViewById(R.id.auth_balance)).setText(ConvertData.moneyToString(money));
	}
}