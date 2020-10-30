package com.example.deluxe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.deluxe.Interface.PresenterView.DepositInterface;
import com.example.deluxe.R;

public class DepositActivity extends AppCompatActivity implements DepositInterface.DepositView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
    }

    @Override
    public void loadView(Class view) {

    }
}