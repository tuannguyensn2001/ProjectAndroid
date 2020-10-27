package com.example.deluxe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deluxe.Entity.User;
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
                signUp.handleSignUp(usernameInput,passwordInput,emailInput);
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
}