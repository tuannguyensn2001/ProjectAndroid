package com.example.deluxe.View.Transaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

<<<<<<< HEAD:app/src/main/java/com/example/deluxe/View/Transaction/TransactionFragment.java
import com.example.deluxe.Interface.PresenterView.Transaction.TransactionInterface;
=======
import com.example.deluxe.Interface.PresenterView.TransactionInterface;
>>>>>>> 6e52742c5d4d535db56eda368b46d114b2de4a74:app/src/main/java/com/example/deluxe/View/TransactionFragment.java
import com.example.deluxe.Presenter.Transaction.TransactionPresenter;
import com.example.deluxe.R;
import com.example.deluxe.View.MainActivity;
import com.example.deluxe.View.SearchUserActivity;

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
				loadView(TransferSearchActivity.class);
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
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		Intent intent = new Intent(getActivity(), view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}
}