package com.example.deluxe.View.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Adapter.ChatUserAdapter;
import com.example.deluxe.Adapter.UserListAdapter;
import com.example.deluxe.Entity.LastMessage;
import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.PresenterView.Chat.ChatUserInterface;
import com.example.deluxe.Presenter.Chat.ChatUserPresenter;
import com.example.deluxe.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatUserFragment extends Fragment implements ChatUserInterface.ChatUserView, ChatUserAdapter.OnUserListener {

	/**
	 * Day la phan khai bao cac loai bien
	 */
	private ArrayList<User> users;
	private SearchView searchBar;
	private UserListAdapter userSearchAdapter;
	private RecyclerView userSearchListView;
	private ConstraintLayout notYetPlaceholder, noResultPlaceholder;
	private ConstraintLayout searchUserPlaceholder;
	private RecyclerView userMessageRecyclerView;
	private ChatUserInterface.ChatUserPresenter chatUserInterfacePresenter;
	private boolean isSearching;
	private boolean isHaveMessage;

	public ChatUserFragment() {
	}

	public static ChatUserFragment newInstance() {
		ChatUserFragment fragment = new ChatUserFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_chat_user, container, false);

		init(view);

		return view;
	}

	private void init(View view) {
//		Inflate the layout for this fragment
		this.chatUserInterfacePresenter = new ChatUserPresenter(this);


//		Phan tim kiem
		this.users = new ArrayList<>();

		this.searchUserPlaceholder = view.findViewById(R.id.include);
		this.userSearchListView = view.findViewById(R.id.search_user_list);
		this.notYetPlaceholder = view.findViewById(R.id.search_not_yet);
		this.noResultPlaceholder = view.findViewById(R.id.search_no_result);

		this.userSearchListView.setItemAnimator(new DefaultItemAnimator());

		this.searchBar = view.findViewById(R.id.search_bar);


//		Phan gan day
		this.userMessageRecyclerView = view.findViewById(R.id.chat_user_list);
		this.userMessageRecyclerView.setHasFixedSize(false);

		this.chatUserInterfacePresenter.showData();
	}

	@Override
	public void onUserClick(int position) {
		chatUserInterfacePresenter.getItem(position);
	}

	@Override
	public void setAdapter(ArrayList<LastMessage> list) {
		ChatUserAdapter chatUserAdapter = new ChatUserAdapter(list, getActivity(), this);
		userMessageRecyclerView.setAdapter(chatUserAdapter);
	}

	@Override
	public void handleIsHaveMessage(boolean isHaveMessage) {
		if (isHaveMessage != this.isHaveMessage) {
			if (isHaveMessage && !isSearching) {
				searchUserPlaceholder.setVisibility(View.GONE);
				userMessageRecyclerView.setVisibility(View.VISIBLE);
			} else {
				userMessageRecyclerView.setVisibility(View.GONE);
				searchUserPlaceholder.setVisibility(View.VISIBLE);
			}
		}
		this.isHaveMessage = isHaveMessage;
	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		Intent intent = new Intent(getActivity(), view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}

	@Override
	public void setList(ArrayList<User> users) {
		this.notYetPlaceholder.setVisibility(android.view.View.GONE);

		if ((this.users.size() == 0) != ((users.size() == 0))) {
			if (this.users.size() != 0) {
				this.userSearchListView.setVisibility(android.view.View.GONE);
				this.noResultPlaceholder.setVisibility(android.view.View.VISIBLE);
			} else {
				this.noResultPlaceholder.setVisibility(android.view.View.GONE);
				this.userSearchListView.setVisibility(android.view.View.VISIBLE);
			}
		}

		this.users.clear();
		this.users = users;

		this.userSearchAdapter = new UserListAdapter(this, users);
		this.userSearchListView.setAdapter(userSearchAdapter);
	}

	@Override
	public void handleOnItemClick(User user) {
		Intent intent = new Intent(this.getContext(), ChatActivity.class);
		intent.putExtra("User", user);
		startActivity(intent);
	}

	@Override
	public void handleIsAlreadySearch(boolean isAlreadySearch) {
		if (this.isSearching != isAlreadySearch) {
			if (isAlreadySearch) {
				userMessageRecyclerView.setVisibility(View.GONE);
				searchUserPlaceholder.setVisibility(View.VISIBLE);

				notYetPlaceholder.setVisibility(View.GONE);
				noResultPlaceholder.setVisibility(View.VISIBLE);
				userSearchListView.setVisibility(View.INVISIBLE);
			} else {
				this.users.clear();
				if (isHaveMessage) {
					userMessageRecyclerView.setVisibility(View.VISIBLE);
					searchUserPlaceholder.setVisibility(View.GONE);
				} else {
					userMessageRecyclerView.setVisibility(View.GONE);
					searchUserPlaceholder.setVisibility(View.VISIBLE);

					userSearchListView.setVisibility(View.GONE);
					noResultPlaceholder.setVisibility(View.GONE);
					notYetPlaceholder.setVisibility(View.VISIBLE);
				}
			}
		}
		this.isSearching = isAlreadySearch;
	}

}