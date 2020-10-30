package com.example.deluxe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deluxe.Interface.PresenterView.MainInterface;
import com.example.deluxe.Presenter.MainPresenter;
import com.example.deluxe.R;
public class MainActivity extends AppCompatActivity implements MainInterface.MainView {

    private MainInterface.MainPresenter mainPresenter;
    private Button logoutButton, napThe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        this.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.handleLogOut();
            }
        });
        this.napThe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.handleNapThe();
            }
        });


    }

    private void init()
    {
        this.mainPresenter = new MainPresenter(this);
        this.logoutButton = (Button) findViewById(R.id.logoutButton);
        this.napThe =(Button) findViewById(R.id.NapThe);
    }

    @Override
    public void loadView(Class view) {
        Intent intent = new Intent(this,view);
        startActivity(intent);
    }
}