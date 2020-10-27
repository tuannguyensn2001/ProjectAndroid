package com.example.deluxe.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Interface.LoginInterface;
import com.example.deluxe.Presenter.LoginPresenter;
import com.example.deluxe.R;

public class LoginActivity extends AppCompatActivity implements LoginInterface.LoginView {
    private Button submitButton;
    private LoginInterface.LoginPresenter Login;
    private EditText username, password;
    private String usernameInput, passwordInput;

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

    }


    public void init() {

        this.submitButton = (Button) findViewById(R.id.submitButton);
        this.Login = new LoginPresenter(this);
        this.username = (EditText) findViewById(R.id.usernameInput);
        this.password = (EditText) findViewById(R.id.emailInput);

    }

    @Override
    public void handleClickButton() {

        if (Rules.min(usernameInput, 6) && Rules.min(passwordInput, 6))
            Login.handleLogin(usernameInput, passwordInput);
        else this.setError(getString(R.string.login_invalid));

    }

    @Override
    public void setError(String error) {
        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleLoginResult(boolean check) {
        if (check) this.setError("Được rồi");

        else this.setError(getString(R.string.login_failed));
    }

    @Override
    public void loadView(Class view) {

    }
}