package com.example.deluxe.Adapter.StatisticsCard;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.deluxe.Entity.Transaction;
import com.example.deluxe.Enum.TransactionType;
import com.example.deluxe.R;
import com.example.deluxe.View.History.StatisticsActivity;
import com.example.deluxe.View.Transaction.TransactionInformationActivity;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StatisticsCardChildViewHolder extends ChildViewHolder {
	private View view;

	public StatisticsCardChildViewHolder(View itemView) {
		super(itemView);
		this.view = itemView;
	}

	public void onBind(final Transaction transaction) {

//		Lay cac thuoc tinh tu giao dich nay ra
		final TransactionType type = transaction.getType();
		long money = transaction.getMoney();
		boolean isComplete = transaction.isComplete();
		Date date = transaction.getDate();
		String userEmail = transaction.getUserEmail();
		String message = transaction.getMessage();


//		Khai bao cac thanh phan cua cardView nay
		final CardView cardView;
		final ConstraintLayout hiddenView, seeMoreView;

		cardView = view.findViewById(R.id.card_view);
		hiddenView = view.findViewById(R.id.hidden_view);
		seeMoreView = view.findViewById(R.id.see_more_view);

		ImageView imageView = view.findViewById(R.id.type_transaction_image);
		TextView moneyText = view.findViewById(R.id.money_transaction);
		TextView dateText = view.findViewById(R.id.date_transaction);

		TextView timeText = view.findViewById(R.id.time_transaction);
		TextView userEmailText = view.findViewById(R.id.user_transaction);
		TextView messageText = view.findViewById(R.id.message_transaction);

		if (userEmail == null)
			userEmailText.setVisibility(View.GONE);
		if (message == null)
			messageText.setVisibility(View.GONE);
		if (type != TransactionType.DEPOSIT)
			seeMoreView.setVisibility(View.VISIBLE);

		if (type == TransactionType.RECEIVE || type == TransactionType.DEPOSIT)
			moneyText.setTextColor(Color.parseColor("#1FA800"));
		else
			moneyText.setTextColor(Color.parseColor("#E60000"));


//		Thay doi thuoc tinh cho cac thanh phan
		if (type == TransactionType.RECEIVE || type == TransactionType.DEPOSIT) {
			moneyText.setTextColor(Color.parseColor("#1FA800"));
			moneyText.setText(new DecimalFormat("+ #,###,###").format(money));
		} else {
			moneyText.setTextColor(Color.parseColor("#E60000"));
			moneyText.setText(new DecimalFormat("- #,###,###").format(money));
		}
		if (type == TransactionType.WITHDRAW && !isComplete)
			moneyText.setTextColor(Color.parseColor("#D8B123"));
		dateText.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date));
		timeText.setText(view.getResources().getString(R.string.statistics_card_time,
				new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault()).format(date)));

		if (type == TransactionType.TRANSFER)
			userEmailText.setText(view.getResources().getString(R.string.statistic_card_user_receiver, userEmail));
		else if (type == TransactionType.RECEIVE)
			userEmailText.setText(view.getResources().getString(R.string.statistic_card_user_sender, userEmail));
		else userEmailText.setVisibility(View.GONE);

		messageText.setText(view.getResources().getString(R.string.statistics_card_message, message));


//		Dat su kien bam vao
		cardView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (hiddenView.getVisibility() == View.VISIBLE) {
					hiddenView.setVisibility(View.GONE);
				} else {
					hiddenView.setVisibility(View.VISIBLE);
				}
			}
		});

		seeMoreView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (type) {
					case USE:
					case WITHDRAW:
					case TRANSFER:
					case RECEIVE:
						((StatisticsActivity) view.getContext()).loadView(TransactionInformationActivity.class, transaction);
					default:
						break;
				}
			}
		});
	}
}
