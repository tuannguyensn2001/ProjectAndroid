package com.example.deluxe.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

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

	User choose;

	ImageView backButton;
	SearchView searchBar;

	long searchWaitTime, deltaTime;

	SearchUserInterface.SearchUserPresenter searchUserPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_user);

		init();

		userListView.setAdapter(adapter);
		userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				choose = (User) userListView.getItemAtPosition(position);
				loadView(TransferActivity.class);
			}
		});

		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(MainActivity.class);
			}
		});

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

		userListView = findViewById(R.id.user_list_search_view);

		backButton = findViewById(R.id.backButton);
		searchBar = findViewById(R.id.search_view);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.transfer_search_action_bar_title));

		searchWaitTime = System.currentTimeMillis();
		deltaTime = 0;

		searchUserPresenter = new SearchUserPresenter(this);
	}

	@Override
	public void loadView(Class view) {

		Intent intent = new Intent(this, view);

		if (view == TransferActivity.class) {
			Bundle bundle = new Bundle();
			bundle.putString("Username", choose.getUser());
			bundle.putString("Email", choose.getEmail());
			bundle.putString("profilePicture", "cc");
			intent.putExtras(bundle);
		}

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