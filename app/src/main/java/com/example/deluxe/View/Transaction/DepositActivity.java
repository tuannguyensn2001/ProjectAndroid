package com.example.deluxe.View.Transaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.DepositInterface;
import com.example.deluxe.Presenter.Transaction.DepositPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.MainActivity;

public class DepositActivity extends AppCompatActivity implements DepositInterface.DepositView {
	ImageView backButton;
	TextView notiText;
	EditText serial, cardCode;
	String serialInput, cardCodeInput;
	Button submitButton;

	DepositInterface.DepositPresenter deposit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deposit);

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
				notiText.setVisibility(View.INVISIBLE);

				serialInput = serial.getText().toString();
				cardCodeInput = cardCode.getText().toString();

				if (Rules.stringLength(serialInput, 10) && Rules.stringLength(cardCodeInput, 14)) {
					deposit.handleDeposit(serialInput, cardCodeInput);
				} else if (Rules.stringLength(serialInput, 0) || Rules.stringLength(cardCodeInput, 0)) {
					setNotification(ErrorMessage.ERR200000);
				} else setNotification(ErrorMessage.ERR200001);
			}

		});
	}

	@Override
	public void loadView(Class view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
		finish();
	}

	private void init() {
		backButton = findViewById(R.id.back_button);

		serial = findViewById(R.id.serial_input);
		cardCode = findViewById(R.id.card_code_input);
		submitButton = findViewById(R.id.submit_button);
		notiText = findViewById(R.id.notification_text);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.deposit_action_bar_title));

		deposit = new DepositPresenter(this);
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
