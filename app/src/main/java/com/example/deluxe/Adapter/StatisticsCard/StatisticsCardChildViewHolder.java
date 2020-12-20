package com.example.deluxe.Adapter.StatisticsCard;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

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

		TextView moneyText = view.findViewById(R.id.money_transaction);
		TextView dateText = view.findViewById(R.id.date_transaction);

		TextView timeText = view.findViewById(R.id.time_transaction);
		TextView userEmailText = view.findViewById(R.id.user_transaction);
		TextView messageText = view.findViewById(R.id.message_transaction);

		if (type != TransactionType.DEPOSIT)
			seeMoreView.setVisibility(View.VISIBLE);


//		Mau me cac kieu o tieu de
		if (type == TransactionType.RECEIVE || type == TransactionType.DEPOSIT) {
			moneyText.setTextColor(ContextCompat.getColor(view.getContext(), R.color.green));
			moneyText.setText(new DecimalFormat("+ #,###,###").format(money));
			moneyText.setCompoundDrawablesWithIntrinsicBounds(
					ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.ic_baseline_north_east_24, null),
					null, null, null);
		} else {
			moneyText.setTextColor(ContextCompat.getColor(view.getContext(), R.color.red));
			moneyText.setText(new DecimalFormat("- #,###,###").format(money));
			moneyText.setCompoundDrawablesWithIntrinsicBounds(
					ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.ic_baseline_south_east_24, null),
					null, null, null);
		}
		if (type == TransactionType.WITHDRAW && !isComplete) {
			moneyText.setTextColor(ContextCompat.getColor(view.getContext(), R.color.yellow));
			moneyText.setCompoundDrawablesWithIntrinsicBounds(
					ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.ic_baseline_money_off_24, null),
					null, null, null);
		}

		dateText.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date));

//		Thoi gian
		timeText.setText(view.getResources().getString(R.string.statistics_card_time));
		Spannable timeSpan = new SpannableString(" " + new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault()).format(date));
		timeSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(view.getContext(), R.color.light_title)), 0, timeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		timeSpan.setSpan(new TypefaceSpan(Typeface.DEFAULT), 0, timeSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		timeText.append(timeSpan);

//		Nguoi choi
		if (type == TransactionType.TRANSFER || type == TransactionType.RECEIVE) {
			userEmailText.setText(view.getResources().getString((type == TransactionType.TRANSFER)
					? R.string.statistic_card_user_receiver
					: R.string.statistic_card_user_sender));
			Spannable emailSpannable;
			emailSpannable = new SpannableString(" " + userEmail);
			emailSpannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(view.getContext(), R.color.light_title)), 0, emailSpannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			emailSpannable.setSpan(new TypefaceSpan(Typeface.DEFAULT), 0, emailSpannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			userEmailText.append(emailSpannable);
		} else userEmailText.setVisibility(View.GONE);

//		Tin nhan
		if (type != TransactionType.DEPOSIT && type != TransactionType.USE) {
			messageText.setText(view.getResources().getString(R.string.statistics_card_message));
			Spannable mess2 = new SpannableString(" " + message);
			mess2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(view.getContext(), R.color.light_title)), 0, mess2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			mess2.setSpan(new TypefaceSpan(Typeface.DEFAULT), 0, mess2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			messageText.append(mess2);
		} else messageText.setVisibility(View.GONE);


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
