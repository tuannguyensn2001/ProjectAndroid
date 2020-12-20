package com.example.deluxe.View.Account;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.Address;
import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.Account.UserInformationChangeInterface;
import com.example.deluxe.Presenter.Account.UserInformationChangePresenter;
import com.example.deluxe.R;


public class UserInformationChange extends AppCompatActivity implements UserInformationChangeInterface.UserInformationChangeView {
	UserInformationChangeInterface.UserInformationChangePresenter userInformationChangePresenter;
	private TextView notiText;
	private EditText username, number, address;
	private Button submitButton;
	private String usernameInput, numberInput, addressInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_information_change);

		init();

		this.submitButton.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				usernameInput = username.getText().toString();
				numberInput = number.getText().toString();
				addressInput = address.getText().toString();

				handleClickButton();
			}
		});

	}


	public void init() {
		this.notiText = findViewById(R.id.notification_text);

		this.username = findViewById(R.id.name_change);
		this.number = findViewById(R.id.number_change);
		this.address = findViewById(R.id.address_change);
		this.submitButton = findViewById(R.id.user_information_button);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.information_change_action_bar_title));
		this.userInformationChangePresenter = new UserInformationChangePresenter(this);
	}

	@Override
	public void handleClickButton() {
		boolean[] list = {Rules.required(usernameInput),
				Rules.min(usernameInput, 0),
				Rules.required(numberInput),
				Rules.min(numberInput, 10),
				Rules.required(addressInput),
		};
		boolean check = validate(list);

		if (check) {
			userInformationChangePresenter.handleInformationChange(usernameInput, numberInput, addressInput);
			setSubmitable(false);
		} else {
			if (!list[0]) {
				username.setError(ErrorMessage.ERR600000.getValue());
			} else if (!list[1]) {
				username.setError(ErrorMessage.ERR600001.getValue());
			}

			if (!list[2]) {
				number.setError(ErrorMessage.ERR600000.getValue());
			} else if (!list[3]) {
				number.setError(ErrorMessage.ERR600001.getValue());
			}

			if (!list[4]) {
				address.setError(ErrorMessage.ERR600000.getValue());
			}
		}

		addTextChanged(username);
		addTextChanged(number);
		addTextChanged(address);
	}

	@Override
	public void setData(Address address) {
		usernameInput = address.getFullname();
		numberInput = address.getPhone();
		addressInput = address.getAddress();
	}

	private boolean validate(boolean[] list) {
		for (boolean i : list) {
			if (!i) return false;
		}
		return true;
	}


	@Override
	public void loadView(Class<? extends View> view) {
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
		notiText.setVisibility(android.view.View.VISIBLE);
	}


	private void addTextChanged(final EditText editText) {
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				notiText.setVisibility(android.view.View.INVISIBLE);
				editText.setError(null);
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	public void setSubmitable(boolean can) {
		submitButton.setClickable(can);
	}
}
