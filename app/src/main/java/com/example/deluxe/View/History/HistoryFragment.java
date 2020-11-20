package com.example.deluxe.View.History;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Interface.PresenterView.History.HistoryInterface;
import com.example.deluxe.Presenter.History.HistoryPresenter;
import com.example.deluxe.R;

import java.util.Objects;

public class HistoryFragment extends Fragment implements HistoryInterface.HistoryView {
	Button statisticsButton, limitButton;
	private HistoryInterface.HistoryPresenter historyPresenter;

	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {

		View view = inflater.inflate(R.layout.fragment_history, container, false);
		init(view);
		this.statisticsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(StatisticsActivity.class);

			}
		});
		this.limitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(LimitActivity.class);
			}
		});
		return view;
	}

	private void init(View v) {
		statisticsButton = v.findViewById(R.id.statistics_button);
		limitButton = v.findViewById(R.id.limit_button);

		historyPresenter = new HistoryPresenter(this);
	}

	@Override
	public void loadView(Class view) {
		((com.example.deluxe.Core.View) Objects.requireNonNull(this.getActivity())).loadView(view);
	}

	@Override
	public void setNotification(Enum e) {

	}
}
