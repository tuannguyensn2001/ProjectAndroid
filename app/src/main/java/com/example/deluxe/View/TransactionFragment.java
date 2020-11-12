package com.example.deluxe.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Interface.PresenterView.TransactionInterface;
import com.example.deluxe.Presenter.TransactionPresenter;
import com.example.deluxe.R;

public class TransactionFragment extends Fragment implements TransactionInterface.TransactionView {
	private TransactionInterface.TransactionPresenter transactionPresenter;
	private Button logoutButton, depositButton, transferButton, ruttienButton;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
		View v = inflater.inflate(R.layout.fragment_transaction, container, false);
		init(v);

		this.logoutButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				transactionPresenter.handleLogOut();
			}
		});
		this.depositButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				transactionPresenter.handleDeposit();
			}
		});

		this.transferButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(SearchUserActivity.class);
			}
		});

		this.ruttienButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(WithdrawActivity.class);
			}
		});

		return v;
	}

	private void init(View v) {
		this.transactionPresenter = new TransactionPresenter(this);
		this.logoutButton = v.findViewById(R.id.logoutButton);
		this.depositButton = v.findViewById(R.id.NapThe);
		this.transferButton = v.findViewById(R.id.transfer);
		this.ruttienButton = v.findViewById(R.id.ruttien);
	}

	@Override
	public void loadView(Class view) {
		((MainActivity)this.getActivity()).loadView(view);
	}

	@Override
	public void setNotification(Enum e) {

	}
}