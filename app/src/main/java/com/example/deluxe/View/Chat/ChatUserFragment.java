package com.example.deluxe.View.Chat;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deluxe.Adapter.ChatUserAdapter;
import com.example.deluxe.Entity.LastMessage;
import com.example.deluxe.Interface.PresenterView.ChatUserInterface;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.Presenter.Chat.ChatUserPresenter;
import com.example.deluxe.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatUserFragment extends Fragment implements ChatUserInterface.ChatUserView, ChatUserAdapter.OnUserListener {

	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	public ChatUserFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment ChatUserFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ChatUserFragment newInstance(String param1, String param2) {
		ChatUserFragment fragment = new ChatUserFragment();
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
	}


	ChatUserInterface.ChatUserPresenter chatUserInterfacePresenter;
	RecyclerView recyclerView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_chat_user, container, false);
		// Inflate the layout for this fragment
		this.chatUserInterfacePresenter = new ChatUserPresenter(this);


		this.recyclerView = view.findViewById(R.id.chat_user_list);
		this.recyclerView.setHasFixedSize(false);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
		this.recyclerView.setLayoutManager(layoutManager);

		this.chatUserInterfacePresenter.showData();

		return view;
	}

	@Override
	public void onUserClick(int position) {
		chatUserInterfacePresenter.getItem(position);
	}

	@Override
	public void setAdapter(ArrayList<LastMessage> list) {
		ChatUserAdapter chatUserAdapter = new ChatUserAdapter(list, getActivity(), this);
		recyclerView.setAdapter(chatUserAdapter);
	}

	@Override
	public void loadView(String email, Class view) {
		Intent intent = new Intent(getActivity(), view);
		intent.putExtra("emailReceiver", email);
		intent.putExtra("emailSender", Auth.getInstance().user().getEmail());
		startActivity(intent);
	}

	@Override
	public void loadView(Class view) {

	}

	@Override
	public void setNotification(Enum e) {

	}
}