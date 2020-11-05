package com.example.deluxe.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.SignUpInterface;
import com.example.deluxe.Presenter.SignUpPresenter;
import com.example.deluxe.R;

public class SignUpActivity extends AppCompatActivity implements SignUpInterface.SignUpView {
	SignUpInterface.SignUpPresenter signUp;
	EditText username, email, password, passwordCheck;
	String usernameInput, emailInput, passwordInput, passwordCheckInput;
	Button submitButton;
	TextView signinButton, notiText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		init();

		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				usernameInput = username.getText().toString();
				emailInput = email.getText().toString();
				passwordInput = password.getText().toString();
				passwordCheckInput = passwordCheck.getText().toString();
				boolean[] list = {
						Rules.isEmail(emailInput),
						Rules.min(passwordInput, 6),
						passwordCheckInput.equals(passwordInput),
						Rules.required(usernameInput),
				};
				boolean check = validate(list);

				if (check) {
					signUp.handleSignUp(usernameInput, passwordInput, emailInput);
				} else {
					if (!list[3]) setNotification(ErrorMessage.ERR000000);
					else if (!list[0]) setNotification(ErrorMessage.ERR000003);
					else if (!list[1]) setNotification(ErrorMessage.ERR000002);
					else if (!list[2]) setNotification(ErrorMessage.ERR000001);
				}
			}


		});

		signinButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				signUp.switchSignInButton();
			}
		});
	}

	private void init() {
		username = findViewById(R.id.usernameInput);
		email = findViewById(R.id.emailInput);
		password = findViewById(R.id.passwordInput);
		passwordCheck = findViewById(R.id.passwordCheckInput);

		submitButton = findViewById(R.id.submitButton);
		signinButton = findViewById(R.id.signinButton);

		signUp = new SignUpPresenter(this);
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

	public boolean validate(boolean[] list) {
		for (boolean i : list) {
			if (!i) return false;
		}
		return true;
	}
}