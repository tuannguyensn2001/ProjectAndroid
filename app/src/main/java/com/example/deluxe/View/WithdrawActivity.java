package com.example.deluxe.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.WithdrawInterface;
import com.example.deluxe.Presenter.WithdrawPresenter;
import com.example.deluxe.R;

public class WithdrawActivity extends AppCompatActivity implements WithdrawInterface.WithdrawView {
	ImageView backButton;
	TextView notiText;
	EditText money, note;
	String moneyInput, noteInput;
	Button submitButton;

	WithdrawPresenter withdrawPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdraw);

		init();

		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(MainActivity.class);
			}
		});

		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleButton();
			}
		});
	}

	private void handleButton() {
		this.notiText.setVisibility(View.INVISIBLE);

		this.moneyInput = money.getText().toString();
		this.noteInput = note.getText().toString();

		if (!Rules.required(moneyInput))
			setNotification(ErrorMessage.ERR400000);
		else if (!Rules.min(moneyInput, 4)) {
			setNotification(ErrorMessage.ERR400001);
		} else {
			withdrawPresenter.handleWithdraw(Double.parseDouble(moneyInput), noteInput);
		}
	}

	private void init() {
		withdrawPresenter = new WithdrawPresenter(this);

		backButton = findViewById(R.id.backButton);

		notiText = findViewById(R.id.notification_text);

		money = findViewById(R.id.money_input);
		note = findViewById(R.id.message_input);

		submitButton = findViewById(R.id.submit_button);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.withdraw_action_bar_title));
	}

	@Override
	public void loadView(Class view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {
		if (e instanceof ErrorMessage) {
			notiText.setTextColor(ContextCompat.getColor(this, R.color.light_error));
			notiText.setText(((ErrorMessage) e).getValue());
		} else {
			notiText.setTextColor(ContextCompat.getColor(this, R.color.light_mainColor));
			notiText.setText(((SuccessMessage) e).getValue());
		}
		notiText.setVisibility(View.VISIBLE);
	}
}