package com.example.deluxe.View.Components;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.PresenterView.Components.SearchUserInterface;
import com.example.deluxe.Presenter.Components.SearchUserPresenter;
import com.example.deluxe.R;

import java.util.ArrayList;

public class SearchUserFragment extends Fragment implements SearchUserInterface.SearchUserView {
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	SearchView searchBar;
	Handler handler;
	Runnable runnable;
	com.example.deluxe.Core.View.ViewUseSearchBar parentContext;
	SearchUserInterface.SearchUserPresenter searchUserPresenter;
	private String mParam1;
	private String mParam2;

	public SearchUserFragment() {
		// Required empty public constructor
	}

	public static SearchUserFragment newInstance(String param1, String param2) {
		SearchUserFragment fragment = new SearchUserFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}

		parentContext = (com.example.deluxe.Core.View.ViewUseSearchBar) (getParentFragment() == null ? getContext() : getParentFragment());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.component_search_user, container, false);


		init(view);

		searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				if (query.length() > 2)
					searchUserPresenter.handleInput(query);

				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {

				handler.removeCallbacks(runnable);
				handler.postDelayed(runnable, 300);

				if (newText.length() > 2) {
					parentContext.handleIsAlreadySearch(true);
					onQueryTextSubmit(newText);
				} else if (newText.length() == 0)
					parentContext.handleIsAlreadySearch(false);

//				TODO dem thoi gian
				return false;
			}
		});

		return view;
	}

	private void init(View view) {
		searchBar = view.findViewById(R.id.search_bar);

		handler = new Handler();
		runnable = new Runnable() {
			@Override
			public void run() {
			}
		};

		searchUserPresenter = new SearchUserPresenter(this);
	}

	@Override
	public void setList(ArrayList<User> users) {
		parentContext.setList(users);
	}
}