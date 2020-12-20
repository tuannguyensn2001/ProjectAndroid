package com.example.deluxe.View.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.Auth.SignUpInterface;
import com.example.deluxe.Presenter.Auth.SignUpPresenter;
import com.example.deluxe.R;

public class SignUpActivity extends AppCompatActivity implements SignUpInterface.SignUpView {
	SignUpInterface.SignUpPresenter signUp;
	EditText username, email, password, passwordCheck;
	String usernameInput, emailInput, passwordInput, passwordCheckInput;
	Button submitButton;
	TextView SignInButton, notiText;
	ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		init();

		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				notiText.setVisibility(View.INVISIBLE);

				usernameInput = username.getText().toString();
				emailInput = email.getText().toString();
				passwordInput = password.getText().toString();
				passwordCheckInput = passwordCheck.getText().toString();

				boolean[] list = {
						Rules.required(usernameInput),
						Rules.required(emailInput),
						Rules.required(passwordInput),
						Rules.required(passwordCheckInput),

						Rules.isEmail(emailInput),
						Rules.min(passwordInput, 6),
						passwordCheckInput.equals(passwordInput),

						Rules.isPassword(passwordInput),
				};
				boolean check = validate(list);

				if (check) {
					signUp.handleSignUp(usernameInput, passwordInput, emailInput);
					setSubmitable(false);
				} else {
					if (!list[0]) {
						username.setError(ErrorMessage.ERR000000.getValue());
					}
					if (!list[1]) {
						email.setError(ErrorMessage.ERR000000.getValue());
					} else if (!list[4]) {
						email.setError(ErrorMessage.ERR000003.getValue());
					}
					if (!list[2]) {
						password.setError(ErrorMessage.ERR000000.getValue());
					} else if (!list[5]) {
						password.setError(ErrorMessage.ERR000002.getValue());
					} else if (!list[7]) {
						password.setError(ErrorMessage.ERR000004.getValue());
					}
					if (!list[3]) {
						passwordCheck.setError(ErrorMessage.ERR000000.getValue());
					} else if (!list[6]) {
						passwordCheck.setError(ErrorMessage.ERR000001.getValue());
					}

				}
			}


		});

		SignInButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				signUp.switchSignInButton();
			}
		});

		addTextChanged(username);
		addTextChanged(email);
		addTextChanged(password);
		addTextChanged(passwordCheck);
	}

	private void init() {
		notiText = findViewById(R.id.notification_text);

		username = findViewById(R.id.username_input);
		email = findViewById(R.id.email_input);
		password = findViewById(R.id.password_input);
		passwordCheck = findViewById(R.id.password_check_input);

		submitButton = findViewById(R.id.submit_button);
		SignInButton = findViewById(R.id.sign_in_button);

		signUp = new SignUpPresenter(this);

		progressBar = findViewById(R.id.progress_bar);
	}

	public void setSubmitable(boolean can) {
		if (can) {
			progressBar.setVisibility(View.INVISIBLE);
			submitButton.setClickable(true);
		} else {
			progressBar.setVisibility(View.VISIBLE);
			submitButton.setClickable(false);
		}
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

	public boolean validate(boolean[] list) {
		for (boolean i : list) {
			if (!i) return false;
		}
		return true;
	}

	public void addTextChanged(final EditText editText) {
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				notiText.setVisibility(View.INVISIBLE);
				editText.setError(null);
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}
}