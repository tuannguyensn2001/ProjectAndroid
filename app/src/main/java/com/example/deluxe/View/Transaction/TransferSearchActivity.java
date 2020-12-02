package com.example.deluxe.View.Transaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Adapter.UserListAdapter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.PresenterView.Transaction.TransferSearchInterface;
import com.example.deluxe.Presenter.Transaction.TransferSearchPresenter;
import com.example.deluxe.R;

import java.util.ArrayList;

public class TransferSearchActivity extends AppCompatActivity implements TransferSearchInterface.TransferSearchView {
	boolean isSearching;
	ArrayList<User> users;

	UserListAdapter adapter;
	RecyclerView userListView;
	ConstraintLayout notYetPlaceholder, noResultPlaceholder;

	SearchView searchBar;

	TransferSearchInterface.TransferSearchPresenter transferSearchPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transfer_search);

		init();

	}

	private void init() {
		transferSearchPresenter = new TransferSearchPresenter(this);

		isSearching = false;
		users = new ArrayList<>();

		userListView = findViewById(R.id.search_user_list);
		notYetPlaceholder = findViewById(R.id.search_not_yet);
		noResultPlaceholder = findViewById(R.id.search_no_result);

		userListView.setItemAnimator(new DefaultItemAnimator());

		searchBar = findViewById(R.id.search_bar);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.transfer_search_action_bar_title));
	}

	@Override
	public void setList(ArrayList<User> users) {
		this.notYetPlaceholder.setVisibility(android.view.View.GONE);

		if ((this.users.size() == 0) != ((users.size() == 0))) {
			if (this.users.size() != 0) {
				this.userListView.setVisibility(android.view.View.GONE);
				this.noResultPlaceholder.setVisibility(android.view.View.VISIBLE);
			} else {
				this.noResultPlaceholder.setVisibility(android.view.View.GONE);
				this.userListView.setVisibility(android.view.View.VISIBLE);
			}
		}
		Log.e("Cc", this.users.size() + ", " + users.size());

		this.users.clear();
		this.users = users;

		this.adapter = new UserListAdapter(this, users);
		this.userListView.setAdapter(adapter);
	}

	@Override
	public void handleOnItemClick(User user) {
		Intent intent = new Intent(this, TransferActivity.class);
		intent.putExtra("User", user);
		startActivity(intent);
	}

	@Override
	public void handleIsAlreadySearch(boolean isAlreadySearch) {
		if (isSearching != isAlreadySearch) {
			if (isAlreadySearch) {
				notYetPlaceholder.setVisibility(android.view.View.GONE);
				userListView.setVisibility(android.view.View.INVISIBLE);
				noResultPlaceholder.setVisibility(android.view.View.VISIBLE);
			} else {
				users.clear();
				userListView.setVisibility(android.view.View.GONE);
				noResultPlaceholder.setVisibility(android.view.View.GONE);
				notYetPlaceholder.setVisibility(android.view.View.VISIBLE);
			}
		}
		isSearching = isAlreadySearch;
	}

	@Override
	public void loadView(Class<? extends View> view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}
}