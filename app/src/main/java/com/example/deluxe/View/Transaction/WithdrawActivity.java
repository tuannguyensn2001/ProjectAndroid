package com.example.deluxe.View.Transaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.Transaction.WithdrawInterface;
import com.example.deluxe.Presenter.Transaction.WithdrawPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.Components.ConfirmPasswordDialog;
import com.example.deluxe.View.MainActivity;
import com.example.deluxe.View.Status.WithdrawSuccessActivity;

import java.text.NumberFormat;

public class WithdrawActivity extends AppCompatActivity implements WithdrawInterface.WithdrawView {
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
		money.addTextChangedListener(new TextWatcher() {
			private String current = "";

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (!s.toString().equals("")) {
					if (!s.toString().equals(current)) {
						String cleanString = s.toString().replaceAll("[,.]", "");
						double parsed = Double.parseDouble(cleanString);
						String formated = NumberFormat.getInstance().format((parsed));

						current = formated;
						money.setText(formated);
						money.setSelection(formated.length());
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
	}

	private void handleSubmitButton() {
		this.notiText.setVisibility(View.INVISIBLE);

		this.moneyInput = money.getText().toString();
		this.noteInput = note.getText().toString();

		if (!Rules.required(moneyInput))
			setNotification(ErrorMessage.ERR400000);
		else if (!Rules.min(moneyInput, 4)) {
			setNotification(ErrorMessage.ERR400001);
		} else {
			final ConfirmPasswordDialog confirmPasswordDialog = new ConfirmPasswordDialog(this);
			confirmPasswordDialog.create().show();
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

	@Override
	public void handleIsUserCorrect(boolean b) {
		if (b) {
			withdrawPresenter.handleWithdraw(Double.parseDouble(moneyInput.replaceAll("[,.]", "")), noteInput);
			loadView(WithdrawSuccessActivity.class);
		} else setNotification(ErrorMessage.ERR500000);
	}

	@Override
	public void handleBackButton() {
		loadView(MainActivity.class);
	}
}