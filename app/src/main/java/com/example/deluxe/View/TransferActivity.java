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

import com.example.deluxe.Entity.User;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.TransferInterface;
import com.example.deluxe.Presenter.TransferPresenter;
import com.example.deluxe.R;

public class TransferActivity extends AppCompatActivity implements TransferInterface.TransferView {
	ImageView backButton;
	TextView username, email;
	TextView notiText;
	EditText money, message;
	Button submitButton;

	String moneyInput, messageInput;

	TransferPresenter transferPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transfer);

		init();

//		Lay ten nguoi dung va email
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			username.setText(bundle.getString("Username", ""));
			email.setText(bundle.getString("Email", ""));
		}

		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(SearchUserActivity.class);
			}
		});

		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleButton();
			}
		});
	}

	private void init() {
		backButton = findViewById(R.id.backButton);
		username = findViewById(R.id.account_username);
		email = findViewById(R.id.account_email);

		notiText = findViewById(R.id.notification_text);

		money = findViewById(R.id.money_input);
		message = findViewById(R.id.message_input);

		submitButton = findViewById(R.id.submit_button);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.transfer_action_bar_title));

		transferPresenter = new TransferPresenter(this);
	}

	@Override
	public void loadView(Class view) {
		Intent intent = new Intent(this, view);

		if (view == TransferSuccessActivity.class) {
			Bundle bundle = new Bundle();

			bundle.putString("Username", this.username.getText().toString());
			bundle.putString("profilePicture", "cc");

			bundle.putString("Money", this.moneyInput);
			bundle.putString("Message", this.messageInput);

			intent.putExtras(bundle);
		}

		startActivity(intent);
		finish();
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
	public void handleButton() {
		this.notiText.setVisibility(View.INVISIBLE);

		moneyInput = money.getText().toString();
		messageInput = message.getText().toString();

		if (!Rules.required(moneyInput))
			setNotification(ErrorMessage.ERR300000);
		else if (!Rules.min(moneyInput, 4))
			setNotification(ErrorMessage.ERR300001);
		else {
			User user = new User(this.username.getText().toString(), null, this.email.getText().toString());
			transferPresenter.handleTransfer(user, Double.parseDouble(moneyInput), messageInput);
		}
	}
}