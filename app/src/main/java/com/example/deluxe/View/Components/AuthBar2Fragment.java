package com.example.deluxe.View.Components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.PresenterView.Components.AuthBarInterface;
import com.example.deluxe.Presenter.Components.AuthBarPresenter;
import com.example.deluxe.R;
import com.squareup.picasso.Picasso;

public class AuthBar2Fragment extends Fragment implements AuthBarInterface.AuthBarView {
	TextView authUsername, authEmail;
	ImageView profilePicture;
	ImageView infoButton;

	AuthBarInterface.AuthBarPresenter authBarPresenter;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_auth_bar_2, container, false);
		init(v);
		return v;
	}

	private void init(View v) {
		this.profilePicture = v.findViewById(R.id.profile_picture);
		this.authUsername = v.findViewById(R.id.auth_username);
		this.authEmail = v.findViewById(R.id.auth_email);
		v.findViewById(R.id.info_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});

		this.authBarPresenter = new AuthBarPresenter(this);
	}

	@Override
	public void setMoney(double money) {
	}

	@Override
	public void setUserInfo(User user) {
		authUsername.setText(user.getUser());
		authEmail.setText(user.getEmail());
		Picasso.get().load(user.getAvatar()).into(profilePicture);
	}
}
