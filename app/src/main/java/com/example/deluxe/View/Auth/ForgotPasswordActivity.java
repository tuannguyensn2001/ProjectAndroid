package com.example.deluxe.View.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.deluxe.Core.View;
import com.example.deluxe.Interface.PresenterView.Auth.ForgotPasswordInterface;
import com.example.deluxe.Presenter.Auth.ForgotPasswordPresenter;
import com.example.deluxe.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordInterface.ForgotPasswordView {

    private ForgotPasswordInterface.ForgotPasswordPresenter forgotPasswordPresenter;
    private EditText emailInput;
    private Button sendButton;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        this.init();

        this.sendButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                forgotPasswordPresenter.handleSendEmailForgotPassword(emailInput.getText().toString());
            }
        });
    }

    public void init()
    {
        this.forgotPasswordPresenter = new ForgotPasswordPresenter(this);
        this.emailInput = findViewById(R.id.email_input);
        this.sendButton = findViewById(R.id.send_email);
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void loadView(Class<? extends View> view) {

    }


    @Override
    public void setNotification(Enum e) {
        Toast.makeText(ForgotPasswordActivity.this,e.toString(), Toast.LENGTH_SHORT).show();
    }


}