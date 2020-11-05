package com.example.deluxe.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.LoginInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Presenter.LoginPresenter;
import com.example.deluxe.R;

public class SignInActivity extends AppCompatActivity implements LoginInterface.LoginView {
	private Button submitButton;
	private TextView signupButton;
	private LoginInterface.LoginPresenter Login;
	private EditText username, password;
	private String usernameInput, passwordInput;
	private TextView notiText;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		init();

		this.submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				usernameInput = username.getText().toString();
				passwordInput = password.getText().toString();

				handleClickButton();
			}
		});

		this.signupButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Login.switchSignUpButton();
			}
		});


		Log.e("user", Auth.getInstance().check() + "");
	}


	public void init() {

		this.notiText = findViewById(R.id.notiText);

		this.signupButton = findViewById(R.id.signupButton);
		this.submitButton = findViewById(R.id.submitButton);
		this.Login = new LoginPresenter(this);

		this.username = findViewById(R.id.usernameInput);
		this.password = findViewById(R.id.passwordInput);

	}

	@Override
	public void handleClickButton() {

		if (Rules.min(usernameInput, 6) && Rules.min(passwordInput, 6)) {
			Login.handleLogin(usernameInput, passwordInput);
		} else {
			if (!Rules.required(usernameInput) || !Rules.required(passwordInput))
				setNotification(ErrorMessage.ERR100000);
			else if (!Rules.min(usernameInput, 6) || !Rules.min(passwordInput, 6))
				setNotification(ErrorMessage.ERR100002);
			else if (!Rules.isEmail(usernameInput))
				setNotification(ErrorMessage.ERR100001);
		}

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
//		TODO dieu khien dang nhap
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