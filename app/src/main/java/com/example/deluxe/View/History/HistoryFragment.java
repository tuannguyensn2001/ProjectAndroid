package com.example.deluxe.View.History;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Adapter.StandardCard.StandardCardChild;
import com.example.deluxe.Adapter.StandardCard.StandardCardDad;
import com.example.deluxe.Adapter.StandardCard.StandardCardRecyclerAdapter;
import com.example.deluxe.Entity.StandardCard;
import com.example.deluxe.Interface.PresenterView.History.HistoryInterface;
import com.example.deluxe.Presenter.History.HistoryPresenter;
import com.example.deluxe.R;

import java.util.ArrayList;
import java.util.Objects;

public class HistoryFragment extends Fragment implements HistoryInterface.HistoryView {
	private HistoryInterface.HistoryPresenter historyPresenter;

	RecyclerView cardList;
	ArrayList<StandardCardDad> cardDads;
	StandardCardRecyclerAdapter recyclerAdapter;


	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {

		View view = inflater.inflate(R.layout.fragment_history, container, false);
		init(view);
		initData();

		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
		cardList.setLayoutManager(layoutManager);

		recyclerAdapter = new StandardCardRecyclerAdapter(cardDads);
		cardList.setAdapter(recyclerAdapter);

//		Mo rong cac nhom dau tien
		recyclerAdapter.toggleGroup(1);
		recyclerAdapter.toggleGroup(0);

		return view;
	}

	private void initData() {
		cardDads = new ArrayList<>();

		ArrayList<StandardCardChild> statistics = new ArrayList<>();
		statistics.add(new StandardCardChild(new StandardCard(false, null, "Tóm tắt", "Tổng thu: 1,000,000", "Tổng chi: 2,000,000", null)));
		statistics.add(new StandardCardChild(new StandardCard(false, null, "Tỷ lệ chi trong tháng", "null", "null", null)));
		statistics.add(new StandardCardChild(new StandardCard(true, StatisticsActivity.class, "Xem them", null, null, null)));

		ArrayList<StandardCardChild> limit = new ArrayList<>();
		limit.add(new StandardCardChild(new StandardCard(false, null, "Hạn mức chi mỗi tháng", "3,000,000,000 / 1,000,000", "Đã vượt: 3000%", null)));
		limit.add(new StandardCardChild(new StandardCard(false, null, "Giới hạn số tiền giao dịch một lần", "1,000,000", "Đã vượt: 69 lần", null)));
		limit.add(new StandardCardChild(new StandardCard(true, LimitActivity.class, "Xem them", null, null, null)));

		cardDads.add(new StandardCardDad("Tóm tắt", statistics));
		cardDads.add(new StandardCardDad("Hạn mức chi", limit));
	}

	private void init(View v) {
		historyPresenter = new HistoryPresenter(this);

		cardList = v.findViewById(R.id.card_list);
	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		((com.example.deluxe.Core.View) Objects.requireNonNull(this.getActivity())).loadView(view);
	}

	@Override
	public void setNotification(Enum e) {

	}
}
