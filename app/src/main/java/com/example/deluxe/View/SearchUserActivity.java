package com.example.deluxe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.deluxe.Interface.PresenterView.SearchUser;
import com.example.deluxe.R;

public class SearchUserActivity extends AppCompatActivity implements SearchUser.SearchUserView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
    }

    @Override
    public void loadView(Class view) {

    }
}