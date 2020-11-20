package com.example.deluxe.View.Transaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Interface.PresenterView.Transaction.TransactionInterface;
import com.example.deluxe.Presenter.Transaction.TransactionPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.MainActivity;
import com.example.deluxe.View.SearchUserActivity;

import java.util.Objects;

public class TransactionFragment extends Fragment implements TransactionInterface.TransactionView {
	private TransactionInterface.TransactionPresenter transactionPresenter;
	private Button depositButton, transferButton, ruttienButton;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
		View v = inflater.inflate(R.layout.fragment_transaction, container, false);
		init(v);

		this.depositButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(DepositActivity.class);
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
		this.depositButton = v.findViewById(R.id.NapThe);
		this.transferButton = v.findViewById(R.id.transfer);
		this.ruttienButton = v.findViewById(R.id.ruttien);
	}

	@Override
	public void loadView(Class view) {
		Intent intent = new Intent(getActivity(), view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}
}