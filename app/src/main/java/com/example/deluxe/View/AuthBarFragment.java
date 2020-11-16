package com.example.deluxe.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Entity.User;
import com.example.deluxe.Interface.PresenterView.AuthBarInterface;
import com.example.deluxe.Presenter.AuthBarPresenter;
import com.example.deluxe.R;

import java.text.DecimalFormat;

public class AuthBarFragment extends Fragment implements AuthBarInterface.AuthBarView {
	TextView authUsername, authEmail, authBalance;

	AuthBarInterface.AuthBarPresenter authBarPresenter;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_auth_bar, container, false);
		init(v);
		return v;
	}

	private void init(View v) {
		this.authUsername = v.findViewById(R.id.auth_username);
		this.authBalance = v.findViewById(R.id.auth_balance);
		this.authEmail = v.findViewById(R.id.auth_email);

		this.authBarPresenter = new AuthBarPresenter(this);
	}

	@Override
	public void loadView(Class view) {

	}

	@Override
	public void setNotification(Enum e) {

	}

	@Override
	public void setMoney(double money) {
		authBalance.setText(new DecimalFormat("#,###,###").format(money));
	}

	@Override
	public void setUserInfo(User user) {
		authUsername.setText(user.getUser());
		authEmail.setText(user.getEmail());
	}
}
