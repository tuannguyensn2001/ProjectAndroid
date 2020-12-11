package com.example.deluxe.View.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.Auth.SignInInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.R;
import com.example.deluxe.View.MainActivity;

public class SignInActivity extends AppCompatActivity implements SignInInterface.SignInView {
	private TextView title, notiText;
	private EditText email, password;
	private Button submitButton;
	private TextView SignUpButton;

	private String emailInput, passwordInput;

	private SignInInterface.SignInPresenter Login;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		init();

		this.submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				emailInput = email.getText().toString();
				passwordInput = password.getText().toString();

				handleClickButton();
			}
		});

		this.SignUpButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Login.switchSignUpButton();
			}
		});


		Log.e("user", Auth.getInstance().check() + "");
	}


	public void init() {
		this.title = findViewById(R.id.title);

		this.notiText = findViewById(R.id.notification_text);

		this.SignUpButton = findViewById(R.id.sign_up_button);
		this.submitButton = findViewById(R.id.submit_button);
		this.Login = new com.example.deluxe.Presenter.Auth.SignInPresenter(this);

		this.email = findViewById(R.id.email_input);
		this.password = findViewById(R.id.password_input);

		findViewById(R.id.username_input).setVisibility(View.GONE);
		findViewById(R.id.password_check_input).setVisibility(View.GONE);

		this.title.setText(getString(R.string.sign_in_title));
		this.submitButton.setText(getString(R.string.sign_in_button));
	}

	@Override
	public void handleClickButton() {
		this.notiText.setVisibility(View.INVISIBLE);

		boolean[] list = {Rules.required(emailInput),
				Rules.min(emailInput, 6),
				Rules.isEmail(emailInput),
				Rules.required(passwordInput),
				Rules.min(passwordInput, 6),
				Rules.isPassword(passwordInput),
		};
		boolean check = validate(list);

		if (check) Login.handleLogin(emailInput, passwordInput);
		else {
			if (!list[0]) {
				email.setError(ErrorMessage.ERR100000.getValue());
			} else if (!list[1]) {
				email.setError(ErrorMessage.ERR100002.getValue());
			} else if (!list[2]) {
				email.setError(ErrorMessage.ERR100001.getValue());
			}

			if (!list[3]) {
				password.setError(ErrorMessage.ERR100000.getValue());
			} else if (!list[4]) {
				password.setError(ErrorMessage.ERR100002.getValue());
			} else if (!list[5]) {
				password.setError(ErrorMessage.ERR100003.getValue());
			}
		}

		addTextChanged(email);
		addTextChanged(password);
	}

	private boolean validate(boolean[] list) {
		for (boolean i : list) {
			if (!i) return false;
		}
		return true;
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
	public void handleLoginResult(boolean check) {
		if (check) setNotification(SuccessMessage.SUC000002);
		else setNotification(ErrorMessage.ERR110001);
	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		Intent intent = new Intent(this, view);
		if (view.equals(MainActivity.class)) {
			startActivity(intent);
			finish();
		}
		startActivity(intent);
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