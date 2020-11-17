package com.example.deluxe.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.TransferInterface;
import com.example.deluxe.Presenter.Transaction.TransferPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.SearchUserActivity;
import com.example.deluxe.View.Status.TransferSuccessActivity;

import java.text.NumberFormat;

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
		money.addTextChangedListener(new TextWatcher() {
			private String current = "";
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(!s.toString().equals(""))
				{
					if(!s.toString().equals(current)){

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
	private void displayConfirmDialog() {
		LayoutInflater inflater = getLayoutInflater();
		View alertLayout = inflater.inflate(R.layout.layout_password_input, null);

		final EditText passwordInput = alertLayout.findViewById(R.id.password_input);
		final CheckBox showPasswordButton = alertLayout.findViewById(R.id.show_password_button);

		showPasswordButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
					passwordInput.setTransformationMethod(null);
				else
					passwordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
			}
		});

		AlertDialog.Builder confirmPasswordDialog = new AlertDialog.Builder(this);
		confirmPasswordDialog.setTitle("Xac nhan mat khau");
		confirmPasswordDialog.setView(alertLayout);
		confirmPasswordDialog.setCancelable(false);
		confirmPasswordDialog.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				;
			}
		});

		confirmPasswordDialog.setPositiveButton("Gui", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(!Rules.required(passwordInput.getText().toString()))
					setNotification(ErrorMessage.ERR500000);
				else if(!Rules.min(passwordInput.getText().toString(),6))
					setNotification(ErrorMessage.ERR500001);
				else {
					User user = new User(username.getText().toString(), null, email.getText().toString());
					transferPresenter.handleTransfer(user, Double.parseDouble(moneyInput.toString().replaceAll("[,.]", "")), messageInput);
				}
			}
		});

		AlertDialog dialog = confirmPasswordDialog.create();
		dialog.show();
	}
	private void init() {
		backButton = findViewById(R.id.back_button);
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
			displayConfirmDialog();
		}
	}
}
