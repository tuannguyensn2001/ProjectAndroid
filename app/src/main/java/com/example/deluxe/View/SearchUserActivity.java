package com.example.deluxe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.deluxe.Adapter.UserListAdapter;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.PresenterView.SearchUser;
import com.example.deluxe.Presenter.SearchUserPresenter;
import com.example.deluxe.R;

import java.util.ArrayList;

public class SearchUserActivity extends AppCompatActivity implements SearchUser.SearchUserView {
    ArrayList<User> users;
    ArrayAdapter<User> adapterArr;
    UserListAdapter adapter;
    ListView userListView;

    SearchView searchBar;

    long searchWaitTime, deltaTime;

    SearchUser.SearchUserPresenter searchUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        init();

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 3) {
                    searchUserPresenter.handleInput(newText);
                }
                return false;
            }

        });
    }

    private void init() {
        users = new ArrayList<User>();

        adapter = new UserListAdapter(users);
        adapterArr = new ArrayAdapter<>(SearchUserActivity.this, R.layout.user_list, users);

        userListView = (ListView) findViewById(R.id.home_userListView);
        userListView.setAdapter(adapter);

        searchBar = (SearchView) findViewById(R.id.home_searchView);

        searchWaitTime = System.currentTimeMillis();
        deltaTime = 0;

        searchUserPresenter = new SearchUserPresenter(this);
    }

    void initAdapter() {

    }

    public void loadView(Class view) {
        Intent intent = new Intent(this, view);
        startActivity(intent);
    }

    @Override
    public void setList(ArrayList<User> users) {
        this.users.clear();
        this.users = users;
        adapter = new UserListAdapter(users);
        userListView.setAdapter(adapter);
    }
}