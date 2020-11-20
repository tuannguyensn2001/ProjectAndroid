package com.example.deluxe.View.Auth;

import android.content.Intent;
import android.os.Bundle;
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
		this.submitButton.setText(getString(R.string.sign_in_submit_button));
	}

	@Override
	public void handleClickButton() {
		this.notiText.setVisibility(View.INVISIBLE);

		if (!Rules.required(emailInput) || !Rules.required(passwordInput))
			setNotification(ErrorMessage.ERR100000);
		else if (!Rules.min(emailInput, 6) || !Rules.min(passwordInput, 6))
			setNotification(ErrorMessage.ERR100002);
		else if (!Rules.isEmail(emailInput))
			setNotification(ErrorMessage.ERR100001);
		else if (!Rules.isPassword(passwordInput))
			setNotification((ErrorMessage.ERR100003));
		else
			Login.handleLogin(emailInput, passwordInput);
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
	public void loadView(Class view) {
		Intent intent = new Intent(this, view);
		if (view.equals(MainActivity.class)) {
			startActivity(intent);
			finish();
		}
		startActivity(intent);
	}

}