package com.example.deluxe.View.History;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.deluxe.Core.View;
import com.example.deluxe.Interface.PresenterView.History.LimitInterface;
import com.example.deluxe.Presenter.History.LimitPresenter;
import com.example.deluxe.R;

public class LimitActivity extends AppCompatActivity implements LimitInterface.LimitView {
	LimitInterface.LimitPresenter limitPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_limit);

		init();
	}

	private void init() {
		this.limitPresenter = new LimitPresenter(this);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.limit_action_bar_title));
	}

	@Override
	public void loadView(Class<? extends View> view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}

}