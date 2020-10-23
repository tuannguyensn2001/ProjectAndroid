package com.example.deluxe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.LoginInterface;
import com.example.deluxe.Presenter.Login;
import com.example.deluxe.R;

public class MainActivity extends AppCompatActivity implements LoginInterface.LoginView {
	private Button submitButton;
	private LoginInterface.LoginPresenter Login;
	private EditText username, password;
	private String usernameInput, passwordInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		init();
		this.submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				usernameInput = username.getText().toString();
				passwordInput = password.getText().toString();
				handleClickButton();
			}
		});
	}


	public void init() {
		this.submitButton = (Button) findViewById(R.id.submitButton);
		this.Login = new Login(this);
		this.username = (EditText) findViewById(R.id.usernameInput);
		this.password = (EditText) findViewById(R.id.passwordInput);
	}

	@Override
	public void handleClickButton() {
		if (Rules.minLength(usernameInput, 6) && Rules.minLength(passwordInput, 6))
			Login.handleLogin(usernameInput, passwordInput);
		else this.setError("Ngắn quá, nhập lại!");
	}

	@Override
	public void setError(String error) {
		Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
	}
}