package com.example.deluxe.View.Transaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.Transaction.DepositInterface;
import com.example.deluxe.R;

public class DepositActivity extends AppCompatActivity implements DepositInterface.DepositView {
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

		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				notiText.setVisibility(View.INVISIBLE);

				serialInput = serial.getText().toString();
				cardCodeInput = cardCode.getText().toString();

				if (Rules.stringLength(serialInput, 10) && Rules.stringLength(cardCodeInput, 14)) {
					deposit.handleDeposit(serialInput, cardCodeInput);
				} else {
					if (!Rules.required(serialInput)) {
						serial.setError(ErrorMessage.ERR200000.getValue());
					} else {
						serial.setError(ErrorMessage.ERR200001.getValue());
					}

					if (!Rules.required(cardCodeInput)) {
						cardCode.setError(ErrorMessage.ERR200000.getValue());
					} else {
						cardCode.setError(ErrorMessage.ERR200001.getValue());
					}
				}
			}

		});

		addTextChanged(serial);
		addTextChanged(cardCode);
	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
		finish();
	}

	private void init() {
		serial = findViewById(R.id.serial_input);
		cardCode = findViewById(R.id.card_code_input);
		submitButton = findViewById(R.id.submit_button);
		notiText = findViewById(R.id.notification_text);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.deposit_action_bar_title));

		deposit = new com.example.deluxe.Presenter.Transaction.DepositPresenter(this);
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

	public void addTextChanged(final EditText editText) {
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				editText.setError(null);
				notiText.setVisibility(View.INVISIBLE);
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}
}
