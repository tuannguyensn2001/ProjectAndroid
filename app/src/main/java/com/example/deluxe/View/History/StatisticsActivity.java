package com.example.deluxe.View.History;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deluxe.Interface.PresenterView.StatisticsInterface;
import com.example.deluxe.R;
import com.example.deluxe.View.MainActivity;

public class StatisticsActivity extends AppCompatActivity implements StatisticsInterface.StatisticsView {
	ImageView backButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);

		init();

		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(MainActivity.class);
			}
		});
	}

	private void init() {
		backButton = findViewById(R.id.back_button);
		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.withdraw_action_bar_title));
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