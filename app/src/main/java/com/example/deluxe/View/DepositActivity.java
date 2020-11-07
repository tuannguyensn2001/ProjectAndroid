package com.example.deluxe.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.DepositInterface;
import com.example.deluxe.Presenter.DepositPresenter;
import com.example.deluxe.R;

import java.text.DecimalFormat;

public class DepositActivity extends AppCompatActivity implements DepositInterface.DepositView {
	TextView authUsername, authEmail, authBalance;
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
	}

	private void init() {
		authUsername = findViewById(R.id.authUsername);
		authBalance = findViewById(R.id.authBalance);
		authEmail = findViewById(R.id.authEmail);

		serial = findViewById(R.id.serialInput);
		cardCode = findViewById(R.id.cardCodeInput);
		submitButton = findViewById(R.id.submitButton);
		notiText = findViewById(R.id.notiText);

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

	@Override
	public void setMoney(double money) {
		authBalance.setText(new DecimalFormat("#,###,###").format(money));
	}

	@Override
	public void setUserInfo(User user) {
		authUsername.setText(user.getUser());
		authEmail.setText(user.getEmail());
	}
}
