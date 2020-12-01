package com.example.deluxe.Adapter.StatisticsCard;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
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
		Spannable sptime1 = new SpannableString(view.getResources().getString(R.string.statistics_card_time) + " ");
		sptime1.setSpan(new ForegroundColorSpan(view.getResources().getColor(R.color.light_mainColor)), 0, sptime1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		sptime1.setSpan(new TypefaceSpan(Typeface.DEFAULT_BOLD), 0, sptime1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		timeText.setText(sptime1);
		Spannable sptime2 = new SpannableString(new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault()).format(date));
		sptime2.setSpan(new ForegroundColorSpan(Color.BLACK), 0, sptime2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		timeText.append(sptime2);

		TextView userEmailText = view.findViewById(R.id.user_transaction);
		Spannable emailTitleSpannable;
		if (type == TransactionType.TRANSFER) {
			emailTitleSpannable = new SpannableString(view.getResources().getString(R.string.statistic_card_user_receiver) + " ");
		} else if (type == TransactionType.RECEIVE) {
			emailTitleSpannable = new SpannableString(view.getResources().getString(R.string.statistic_card_user_receiver) + " ");
		} else {
			emailTitleSpannable = new SpannableString("");
		}
		emailTitleSpannable.setSpan(new ForegroundColorSpan(view.getResources().getColor(R.color.light_mainColor)), 0, emailTitleSpannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		emailTitleSpannable.setSpan(new TypefaceSpan(Typeface.DEFAULT_BOLD), 0, emailTitleSpannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		Spannable emailSpannable;
		if (userEmail != null) {
			emailSpannable = new SpannableString(userEmail);
		} else {
			emailSpannable = new SpannableString("");
		}
		emailSpannable.setSpan(new ForegroundColorSpan(Color.BLACK), 0, emailSpannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


		TextView messageText = view.findViewById(R.id.message_transaction);
		Spannable mess1 = new SpannableString(view.getResources().getString(R.string.statistics_card_message) + " ");
		mess1.setSpan(new ForegroundColorSpan(view.getResources().getColor(R.color.light_mainColor)), 0, mess1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		mess1.setSpan(new TypefaceSpan(Typeface.DEFAULT_BOLD), 0, mess1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		messageText.setText(mess1);
		if (message != null) {
			Spannable mess2 = new SpannableString(message);
			mess2.setSpan(new ForegroundColorSpan(Color.BLACK), 0, mess2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			messageText.append(mess2);
		}


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


		if (type == TransactionType.TRANSFER || type == TransactionType.RECEIVE) {
			userEmailText.setText(emailTitleSpannable);
			userEmailText.append(emailSpannable);
		} else userEmailText.setVisibility(View.GONE);


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
