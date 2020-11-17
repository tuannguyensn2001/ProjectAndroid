package com.example.deluxe.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
		username = findViewById(R.id.accountUsername);
		email = findViewById(R.id.accountEmail);

		notiText = findViewById(R.id.notiText);

		money = findViewById(R.id.moneyTransferInput);
		message = findViewById(R.id.messageTransferInput);

		submitButton = findViewById(R.id.submitButton);

		transferPresenter = new TransferPresenter(this);
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
		} else
		{
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