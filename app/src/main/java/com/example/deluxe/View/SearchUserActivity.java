package com.example.deluxe.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deluxe.Adapter.UserListAdapter;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.PresenterView.SearchUserInterface;
import com.example.deluxe.Presenter.SearchUserPresenter;
import com.example.deluxe.R;

import java.util.ArrayList;

public class SearchUserActivity extends AppCompatActivity implements SearchUserInterface.SearchUserView {
	ArrayList<User> users;
	ArrayAdapter<User> adapterArr;
	UserListAdapter adapter;
	ListView userListView;

	SearchView searchBar;

	long searchWaitTime, deltaTime;

	SearchUserInterface.SearchUserPresenter searchUserPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_user);

		init();

		searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				if (query.length() > 2)
					searchUserPresenter.handleInput(query);

				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				if (newText.length() > 2) {
					searchUserPresenter.handleInput(newText);
				}
//				TODO dem thoi gian
				return false;
			}

		});
	}

	private void init() {
		users = new ArrayList<User>();

		adapter = new UserListAdapter(users);
		adapterArr = new ArrayAdapter<>(SearchUserActivity.this, R.layout.user_list, users);

		userListView = findViewById(R.id.home_userListView);
		userListView.setAdapter(adapter);
		userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				TODO xu ly phan an
				loadView(TransferActivity.class);
			}
		});

		searchBar = findViewById(R.id.home_searchView);

		searchWaitTime = System.currentTimeMillis();
		deltaTime = 0;

		searchUserPresenter = new SearchUserPresenter(this);
	}

	@Override
	public void loadView(Class view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}

	@Override
	public void setList(ArrayList<User> users) {

		this.users.clear();
		this.users = users;
		adapter = new UserListAdapter(users);
		userListView.setAdapter(adapter);
	}
}