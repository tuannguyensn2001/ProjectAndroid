package com.example.deluxe.View.Components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Interface.PresenterView.Components.ActionBarInterface;
import com.example.deluxe.Presenter.Components.ActionBarPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.MainActivity;

import java.util.Objects;

public class ActionBarFragment extends Fragment implements ActionBarInterface.ActionBarView {
	ActionBarInterface.ActionBarPresenter actionBarPresenter;

	ImageView backButton, homeButton;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_action_bar, container, false);
		init(v);
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ViewUseActionBar) Objects.requireNonNull(getActivity())).handleBackButton();
			}
		});
		homeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((com.example.deluxe.Core.View) Objects.requireNonNull(getActivity())).loadView(MainActivity.class);
			}
		});
		return v;
	}

	private void init(View v) {
		this.backButton = v.findViewById(R.id.back_button);
		this.homeButton = v.findViewById(R.id.home_button);

		this.actionBarPresenter = new ActionBarPresenter(this);
	}

	@Override
	public void loadView(Class view) {

	}

	@Override
	public void setNotification(Enum e) {

	}
}
