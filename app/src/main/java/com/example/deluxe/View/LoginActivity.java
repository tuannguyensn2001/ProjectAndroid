package com.example.deluxe.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.LoginInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Presenter.LoginPresenter;
import com.example.deluxe.R;

public class LoginActivity extends AppCompatActivity implements LoginInterface.LoginView {
	private Button submitButton, signupButton;
	private LoginInterface.LoginPresenter Login;
	private EditText username, password;
	private String usernameInput, passwordInput;
	private TextView errorText;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

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

		this.errorText = (TextView) findViewById(R.id.errorText);

		this.signupButton = (Button) findViewById(R.id.signupButton);
		this.submitButton = (Button) findViewById(R.id.submitButton);
		this.Login = new LoginPresenter(this);

		this.username = (EditText) findViewById(R.id.usernameInput);
		this.password = (EditText) findViewById(R.id.passwordInput);

	}

	@Override
	public void handleClickButton() {

		if (Rules.min(usernameInput, 6) && Rules.min(passwordInput, 6)) {
			Login.handleLogin(usernameInput, passwordInput);

		} else this.setError(getString(R.string.login_invalid));

	}

	@Override
	public void setError(String error) {
		errorText.setVisibility(View.VISIBLE);
		errorText.setText(error);
		Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void handleLoginResult(boolean check) {
		if (check) this.setError("Được rồi");

		else this.setError(getString(R.string.login_failed));
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

/*
* package com.example.deluxe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.PresenterView.SignUpInterface;
import com.example.deluxe.Presenter.SignUpPresenter;
import com.example.deluxe.R;

public class SignUpActivity extends AppCompatActivity implements SignUpInterface.SignUpView {
    SignUpInterface.SignUpPresenter signUp;
    EditText username, email, password, passwordCheck;
    String usernameInput, emailInput, passwordInput, passwordCheckInput;
    Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameInput=username.getText().toString();
                emailInput=email.getText().toString();
                passwordInput=password.getText().toString();
                passwordCheckInput=passwordCheck.getText().toString();
//                if(Rules.isEmail(emailInput) && Rules.min(passwordInput,6) && passwordCheckInput.equals(passwordInput))
//                {
//                    signUp.handleSignUp(usernameInput, passwordInput, emailInput);
//                } else setError("Thông tin không hợp lệ");
                boolean[] list = {
                  Rules.isEmail(emailInput),
                  Rules.min(passwordInput,6),
                  passwordCheckInput.equals(passwordInput),
                  Rules.required(usernameInput),
                };
                boolean check = validate(list);

                if (check) {
                    signUp.handleSignUp(usernameInput, passwordInput, emailInput);
                } else {
                    setError("Thông tin không hợp lệ");
                }
            }


        });
    }

    private void init()
    {
        username=(EditText) findViewById(R.id.usernameInput);
        email=(EditText) findViewById(R.id.emailInput);
        password=(EditText) findViewById(R.id.passwordInput);
        passwordCheck=(EditText) findViewById(R.id.passwordCheckInput);
        submitButton=(Button) findViewById(R.id.signupButton);
        signUp= new SignUpPresenter(this);
    }

    @Override
    public void loadView(Class view) {
        Intent intent=new Intent(this,view);
        startActivity(intent);
    }

    @Override
    public void setError(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }




}*/