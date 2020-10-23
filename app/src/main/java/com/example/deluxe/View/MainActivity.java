package com.example.deluxe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.deluxe.Interface.LoginInterface;
import com.example.deluxe.Presenter.Login;
import com.example.deluxe.R;

public class MainActivity extends AppCompatActivity implements LoginInterface.LoginView {
    private Button submitButton;
    private LoginInterface.LoginPresenter Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
        this.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickButton();
            }
        });
    }


    public void init()
    {
        this.submitButton = (Button) findViewById(R.id.button);
        this.Login = new Login(this);
    }

    @Override
    public void handleClickButton() {
        String username = "A";
        String password = "B";
        Login.handleLogin(username,password);
    }

    @Override
    public void setError()
    {
        Toast.makeText(MainActivity.this, "Có lỗi", Toast.LENGTH_SHORT).show();
    }
}