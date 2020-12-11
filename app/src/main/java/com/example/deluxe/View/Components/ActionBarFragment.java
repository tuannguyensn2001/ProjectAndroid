package com.example.deluxe.View.Components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Interface.PresenterView.ActionBarInterface;
import com.example.deluxe.Presenter.Components.ActionBarPresenter;
import com.example.deluxe.R;

public class ActionBarFragment extends Fragment implements ActionBarInterface.ActionBarView {
	ActionBarInterface.ActionBarPresenter actionBarPresenter;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_action_bar, container, false);
		init(v);
		return v;
	}

	private void init(View v) {
		this.actionBarPresenter = new ActionBarPresenter(this);
	}

	@Override
	public void loadView(Class view) {

	}

	@Override
	public void setNotification(Enum e) {

	}
}
