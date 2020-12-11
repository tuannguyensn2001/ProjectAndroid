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
import com.example.deluxe.Interface.PresenterView.Transaction.WithdrawInterface;
import com.example.deluxe.Presenter.Transaction.WithdrawPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.Components.ConfirmPasswordDialog;
import com.example.deluxe.View.Status.WithdrawSuccessActivity;

import java.text.NumberFormat;

public class WithdrawActivity extends AppCompatActivity implements WithdrawInterface.WithdrawView {
	TextView notiText;
	EditText money, note;
	String moneyInput, noteInput;
	Button submitButton;

	ConfirmPasswordDialog confirmPasswordDialog;

	WithdrawPresenter withdrawPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdraw);

		init();
		money.addTextChangedListener(new TextWatcher() {
			private String current = "";

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (!s.toString().equals("")) {
					if (!s.toString().equals(current)) {
						String cleanString = s.toString().replaceAll("[,.]", "");
						double parsed = Double.parseDouble(cleanString);
						String formatted = NumberFormat.getInstance().format((parsed));

						current = formatted;
						money.setText(formatted);
						money.setSelection(formatted.length());
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleSubmitButton();
			}
		});

		money.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				money.setError(null);
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	private void handleSubmitButton() {
		this.notiText.setVisibility(View.INVISIBLE);

		this.moneyInput = money.getText().toString();
		this.noteInput = note.getText().toString();

		if (!Rules.required(moneyInput)) {
//			money.setBackgroundResource(R.drawable.field_corner);
			money.setError(ErrorMessage.ERR400000.getValue());
		} else if (!Rules.min(moneyInput, 4)) {
//			money.setBackgroundResource(R.drawable.field_corner);
			money.setError(ErrorMessage.ERR400001.getValue());
		} else {
			withdrawPresenter.checkBalance(Double.parseDouble(moneyInput.replaceAll("[,.]", "")));
		}
	}

	private void init() {
		withdrawPresenter = new WithdrawPresenter(this);

		notiText = findViewById(R.id.notification_text);

		money = findViewById(R.id.money_input);
		note = findViewById(R.id.message_input);

		submitButton = findViewById(R.id.submit_button);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.withdraw_action_bar_title));
	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
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

	@Override
	public void handleIsUserCorrect(boolean b) {
		if (b) {
			withdrawPresenter.handleWithdraw(Double.parseDouble(moneyInput.replaceAll("[,.]", "")), noteInput);
			loadView(WithdrawSuccessActivity.class);
			finish();
		} else
			confirmPasswordDialog.setNotification(ErrorMessage.ERR500003);
	}

	@Override
	public void handleDialog() {
		confirmPasswordDialog = new ConfirmPasswordDialog(this);
	}
}