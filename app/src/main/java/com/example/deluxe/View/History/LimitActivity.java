package com.example.deluxe.View.History;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.Transaction;
import com.example.deluxe.Interface.PresenterView.History.LimitInterface;
import com.example.deluxe.Presenter.History.LimitPresenter;
import com.example.deluxe.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LimitActivity extends AppCompatActivity implements LimitInterface.LimitView {
	LimitInterface.LimitPresenter limitPresenter;

	private String[] tabList = {"Hạn mức tháng", "Hạn mức mỗi lần"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_limit);

		init();
	}

	private void init() {
		this.limitPresenter = new LimitPresenter(this);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.limit_action_bar_title));

		ViewPager2 statisticsPager = findViewById(R.id.statistics_fragment);
		statisticsPager.setAdapter(new LimitTabAdapter(this));
		TabLayout tabLayout = findViewById(R.id.tab_bar);
		new TabLayoutMediator(tabLayout, statisticsPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
			@Override
			public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
				tab.setText(tabList[position]);
			}
		}).attach();
	}

	@Override
	public void loadView(Class<? extends View> view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}

	@Override
	public void loadView(Class<? extends View> view, Transaction transaction) {
		Intent intent = new Intent(this, view);

		intent.putExtra("Transaction", transaction);

		startActivity(intent);
	}
}