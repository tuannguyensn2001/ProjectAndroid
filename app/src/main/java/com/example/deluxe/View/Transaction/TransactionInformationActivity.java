package com.example.deluxe.View.Transaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.Transaction;
import com.example.deluxe.Enum.TransactionType;
import com.example.deluxe.Helper.ConvertData;
import com.example.deluxe.Interface.PresenterView.Transaction.TransactionInformationInterface;
import com.example.deluxe.Presenter.Transaction.TransactionInformationPresenter;
import com.example.deluxe.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TransactionInformationActivity extends AppCompatActivity implements TransactionInformationInterface.TransactionInformationView {
	TransactionInformationInterface.TransactionInformationPresenter transactionInformationPresenter;
	Transaction transaction;

	String[] titleList = {
			"Loại giao dịch",
			"Số tiền",
			"Tình trạng",
			"Thời gian giao dịch",
			"Người chơi",
			"Lời nhắn",
			"Giỏ hàng",
	};

	LinearLayout[] viewList;
	TextView[] title;
	TextView[] subtitle;
	FrameLayout[] content;

	ListView listView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_information);

		init();
		transaction = (Transaction) getIntent().getSerializableExtra("Transaction");
		if (transaction != null) {
			onBind();
		}
	}

	private void onBind() {
		final TransactionType type = transaction.getType();
		long money = transaction.getMoney();
		boolean isComplete = transaction.isComplete();

//		0
		subtitle[0].setText(transaction.getType().getValue());

//		1
		subtitle[1].setText(ConvertData.moneyToString(transaction.getMoney()));
		if (type == TransactionType.RECEIVE || type == TransactionType.DEPOSIT) {
			subtitle[1].setTextColor(ContextCompat.getColor(this, R.color.green));
			subtitle[1].setText(new DecimalFormat("+ #,###,###").format(money));
			subtitle[1].setCompoundDrawablesWithIntrinsicBounds(
					ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_north_east_24, null),
					null, null, null);
		} else {
			subtitle[1].setTextColor(ContextCompat.getColor(this, R.color.red));
			subtitle[1].setText(new DecimalFormat("- #,###,###").format(money));
			subtitle[1].setCompoundDrawablesWithIntrinsicBounds(
					ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_south_east_24, null),
					null, null, null);
		}
		if (type == TransactionType.WITHDRAW && !isComplete) {
			subtitle[1].setTextColor(ContextCompat.getColor(this, R.color.yellow));
			subtitle[1].setCompoundDrawablesWithIntrinsicBounds(
					ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_money_off_24, null),
					null, null, null);
		}


//		2
		subtitle[2].setText(transaction.isComplete() ? "Xong roi" : "Chua xong");
		if (!isComplete) subtitle[2].setTextColor(ContextCompat.getColor(this, R.color.yellow));

//		3
		subtitle[3].setText(new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault()).format(transaction.getDate()));
//		content[3].setVisibility(android.view.View.VISIBLE);
//		getSupportFragmentManager().beginTransaction().replace(R.id.info_frame,
//				new AuthBarFragment()).commit();

		if (type == TransactionType.TRANSFER || type == TransactionType.RECEIVE)
			subtitle[4].setText(transaction.getUserEmail());
		else
			viewList[4].setVisibility(android.view.View.GONE);

		if (type != TransactionType.DEPOSIT && type != TransactionType.USE)
			subtitle[5].setText(transaction.getMessage().isEmpty() ? "Rỗng tuếch" : transaction.getMessage());
		else
			viewList[5].setVisibility(android.view.View.GONE);

		viewList[6].setVisibility(android.view.View.GONE);
	}

	private void init() {
		this.transactionInformationPresenter = new TransactionInformationPresenter(this);
		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.transaction_info_action_bar_title));

		this.viewList = new LinearLayout[7];
		this.title = new TextView[7];
		this.subtitle = new TextView[7];
		this.content = new FrameLayout[7];
		for (int i = 0; i < 7; i++) {
			String includeID = "include" + i;
			int resID = getResources().getIdentifier(includeID, "id", getPackageName());
			viewList[i] = findViewById(resID);
			title[i] = ((TextView) viewList[i].findViewById(R.id.info_title));
			subtitle[i] = ((TextView) viewList[i].findViewById(R.id.info_subtitle));
			content[i] = (FrameLayout) viewList[i].findViewById(R.id.info_frame);

			title[i].setText(titleList[i]);
		}
	}

	@Override
	public void loadView(Class<? extends View> view) {
		Intent intent = new Intent(this, view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}
}