package com.example.deluxe.Adapter.StandardCard;

import android.view.View;
import android.widget.TextView;

import com.example.deluxe.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class StandardCardDadViewHolder extends GroupViewHolder {
	private TextView cardDadTitle;

	public StandardCardDadViewHolder(View itemView) {
		super(itemView);

		cardDadTitle = itemView.findViewById(R.id.card_dad_title);
		itemView.setClickable(false);
	}

	@Override
	public void expand() {
		super.expand();
	}

	@Override
	public void collapse() {
		super.collapse();
	}

	public void setGroupName(StandardCardDad group) {
		cardDadTitle.setText(group.getTitle());
	}
}
