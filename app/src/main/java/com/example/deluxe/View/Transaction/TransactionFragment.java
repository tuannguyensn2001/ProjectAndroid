package com.example.deluxe.View.Transaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Interface.PresenterView.Transaction.TransactionInterface;
import com.example.deluxe.Presenter.Transaction.TransactionPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.Shopping.ProductActivity;

public class TransactionFragment extends Fragment implements TransactionInterface.TransactionView {
	private TransactionInterface.TransactionPresenter transactionPresenter;
	private ImageView depositButton, withdrawButton, transferButton, useButton;

	public static TransactionFragment newInstance() {
		TransactionFragment fragment = new TransactionFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

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
				loadView(TransferSearchActivity.class);
			}
		});

		this.withdrawButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(WithdrawActivity.class);
			}
		});

		this.useButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadView(ProductActivity.class);
			}
		});

		return v;
	}

	private void init(View v) {
		this.transactionPresenter = new TransactionPresenter(this);
		this.depositButton = v.findViewById(R.id.deposit_button);
		this.withdrawButton = v.findViewById(R.id.withdraw_button);
		this.transferButton = v.findViewById(R.id.transfer_button);
		this.useButton = v.findViewById(R.id.use_button);
	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		Intent intent = new Intent(getActivity(), view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}
}