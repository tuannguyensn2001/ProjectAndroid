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
import com.example.deluxe.Interface.PresenterView.TransferInterface;
import com.example.deluxe.Presenter.TransferPresenter;
import com.example.deluxe.R;

public class TransferActivity extends AppCompatActivity implements TransferInterface.TransferView {
	ImageView backButton;
	TextView username, email;
	EditText money, message;
	Button submitButton;

	String moneyInput, messageInput;

	TransferPresenter transferPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transfer);

		init();

		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(SearchUserActivity.class);
			}
		});

		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				moneyInput = money.getText().toString();
				messageInput = message.getText().toString();

				handleButton();
			}
		});
	}

	private void init() {
		backButton = findViewById(R.id.backButton);
		username = findViewById(R.id.accountUsername);
		email = findViewById(R.id.accountEmail);

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
//		TODO lam xong cai notiText de con bo comment

//        if (e instanceof ErrorMessage) {
//            notiText.setTextColor(ContextCompat.getColor(this, R.color.light_error));
//            notiText.setText(((ErrorMessage) e).getValue());
//        } else
//        {
//            notiText.setTextColor(ContextCompat.getColor(this, R.color.light_mainColor));
//            notiText.setText(((SuccessMessage) e).getValue());
//        }
//        notiText.setVisibility(View.VISIBLE);
	}

	@Override
	public void handleButton() {
		User user = new User(this.username.toString(), null, this.email.toString());
		transferPresenter.handleTransfer(user, Double.parseDouble(moneyInput), messageInput);
	}
}