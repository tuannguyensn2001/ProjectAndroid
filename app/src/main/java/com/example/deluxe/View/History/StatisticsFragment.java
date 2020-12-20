package com.example.deluxe.View.History;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Adapter.StatisticsCard.StatisticsCardChild;
import com.example.deluxe.Adapter.StatisticsCard.StatisticsCardDad;
import com.example.deluxe.Adapter.StatisticsCard.StatisticsCardRecyclerAdapter;
import com.example.deluxe.Entity.Transaction;
import com.example.deluxe.Interface.PresenterView.History.StatisticsInterface;
import com.example.deluxe.Presenter.History.StatisticsPresenter;
import com.example.deluxe.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StatisticsFragment extends Fragment implements StatisticsInterface.StatisticsView.StatisticsFragment {
	private static final String ARG_POSITION = "position";


	StatisticsInterface.StatisticsPresenter statisticsPresenter;

	RecyclerView transactionRecyclerList;
	private int pos;

	public StatisticsFragment() {
		// Required empty public constructor
	}

	public static StatisticsFragment newInstance(int pos) {
		StatisticsFragment fragment = new StatisticsFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_POSITION, pos);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			pos = getArguments().getInt(ARG_POSITION);
		}
	}

	private void initData() {
		statisticsPresenter.getTransactionArray(pos);
	}

	private void init(View view) {
		this.statisticsPresenter = new StatisticsPresenter(this);

		transactionRecyclerList = view.findViewById(R.id.transaction_list);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_statistics, container, false);

		init(view);
		initData();

		return view;
	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		((com.example.deluxe.Core.View) Objects.requireNonNull(this.getActivity())).loadView(view);
	}

	@Override
	public void setNotification(Enum e) {

	}

	@Override
	public void getView(HashMap<Date, ArrayList<Transaction>> transactions) {
		ArrayList<StatisticsCardDad> transactionDads = new ArrayList<>();

//		Trong rong len dau
		{
			ArrayList<Transaction> transactionsChild = transactions.get(null);
			if (transactionsChild != null) {
				String title = Objects.requireNonNull(getContext()).getResources().getString(R.string.statistics_card_dad_title2);
				ArrayList<StatisticsCardChild> statisticsCardChildren = new ArrayList<>();
				for (Transaction transaction : transactionsChild) {
					statisticsCardChildren.add(new StatisticsCardChild(transaction));
				}

				transactionDads.add(new StatisticsCardDad(title, statisticsCardChildren));
			}
		}

//		Khong trong rong
		for (Map.Entry<Date, ArrayList<Transaction>> pair : transactions.entrySet()) {
//			Tieu de ngay
			String title;
			Date titleDate = pair.getKey();
			if (titleDate != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(titleDate);
				int month = cal.get(Calendar.MONTH);
				int year = cal.get(Calendar.YEAR);
				title = getContext().getResources().getString(R.string.statistics_card_dad_title1, month + 1, year);

				//Danh sach giao dich trong thang
				ArrayList<Transaction> transactionsChild = pair.getValue();
				ArrayList<StatisticsCardChild> statisticsCardChildren = new ArrayList<>();
				for (Transaction transaction : transactionsChild) {
					statisticsCardChildren.add(new StatisticsCardChild(transaction));
				}
				transactionDads.add(new StatisticsCardDad(title, statisticsCardChildren));
			}
		}


//		Gan adapter, cac thu cac thu
		StatisticsCardRecyclerAdapter recyclerAdapter = new StatisticsCardRecyclerAdapter(transactionDads);
		transactionRecyclerList.setAdapter(recyclerAdapter);
	}
}