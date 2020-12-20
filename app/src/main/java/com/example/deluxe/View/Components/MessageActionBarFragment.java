package com.example.deluxe.View.Components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.deluxe.R;
import com.example.deluxe.View.MainActivity;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageActionBarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageActionBarFragment extends Fragment {
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "Email";
	ImageView backButton, infoButton;
	private String email;

	public MessageActionBarFragment() {
		// Required empty public constructor
	}

	public static MessageActionBarFragment newInstance(String param1) {
		MessageActionBarFragment fragment = new MessageActionBarFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			email = getArguments().getString(ARG_PARAM1);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_message_action_bar, container, false);
		init(v);
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Objects.requireNonNull(getActivity()).onBackPressed();
			}
		});
		infoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((com.example.deluxe.Core.View) Objects.requireNonNull(getActivity())).loadView(MainActivity.class);
			}
		});
		return v;
	}

	private void init(View v) {
		this.backButton = v.findViewById(R.id.back_button);
		this.infoButton = v.findViewById(R.id.info_button);
	}
}